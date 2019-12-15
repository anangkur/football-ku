package com.anangkur.footbalku.feature.leagueDetail.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.anangkur.footbalku.R
import com.anangkur.footbalku.base.BaseFragment
import com.anangkur.footbalku.data.model.LeagueDetail
import com.anangkur.footbalku.util.obtainViewModel
import com.anangkur.footbalku.util.setImageUrl
import com.anangkur.footbalku.util.showSnackbarLong
import kotlinx.android.synthetic.main.fragment_detail_league.*

class DetailLeagueFragment: BaseFragment<DetailLeagueViewModel>(), DetailLeagueActionListener{

    override val mLayout: Int
        get() = R.layout.fragment_detail_league
    override val mViewModel: DetailLeagueViewModel
        get() = obtainViewModel(DetailLeagueViewModel::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.dataFromArgs = arguments?.getParcelable(ARGS_LEAGUE)
        setupDataToView()
    }

    private fun setupDataToView(){
        mViewModel.dataFromArgs?.let {data ->
            iv_league.setImageUrl(data.strBadge)
            tv_title_league.text = data.strLeague
            tv_desc_league.text = data.strDescriptionEN
            btn_facebook.setOnClickListener { this.onClickWebsite(data.strFacebook) }
            btn_twitter.setOnClickListener { this.onClickWebsite(data.strTwitter) }
            btn_website.setOnClickListener { this.onClickWebsite(data.strWebsite) }
        }
    }

    override fun onClickWebsite(url: String) {
        try{
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse("https://$url")
            startActivity(i)
        }catch (e: Exception){
            requireActivity().showSnackbarLong(e.message?:"")
        }
    }

    companion object{

        private const val ARGS_LEAGUE = "ARGS_LEAGUE"

        fun newInstance(data: LeagueDetail?) = DetailLeagueFragment().apply {
            arguments = Bundle(1).apply {
                putParcelable(ARGS_LEAGUE, data)
            }
        }
    }
}