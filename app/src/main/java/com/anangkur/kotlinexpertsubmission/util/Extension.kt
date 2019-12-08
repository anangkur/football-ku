package com.anangkur.kotlinexpertsubmission.util

import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.EventFavourite
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.data.ViewModelFactory
import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.MyDatabaseOpenHelper
import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.*

fun Activity.showSnackbarLong(message: String){
    Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
}

fun ImageView.setImageUrl(url: String){
    Glide.with(this)
        .load(url)
        .apply(RequestOptions().error(R.color.gray))
        .apply(RequestOptions().placeholder(createCircularProgressDrawable(this.context)))
        .apply(RequestOptions().centerCrop())
        .into(this)
}

fun ImageView.setImageUrlDarkBg(url: String){
    Glide.with(this)
        .load(url)
        .apply(RequestOptions().error(R.color.gray))
        .apply(RequestOptions().placeholder(createCircularProgressDrawableLight(this.context)))
        .apply(RequestOptions().centerCrop())
        .into(this)
}

fun hideSoftKeyboard(activity: Activity) {
    val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        activity.currentFocus!!.windowToken, 0
    )
}

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(this.requireContext())).get(viewModelClass)

fun RecyclerView.setupRecyclerViewGrid(context: Context, spanCount: Int){
    this.apply {
        itemAnimator = DefaultItemAnimator()
        layoutManager = GridLayoutManager(context, spanCount, GridLayoutManager.VERTICAL, false)
    }
}

fun RecyclerView.setupRecyclerViewLinear(context: Context){
    this.apply {
        itemAnimator = DefaultItemAnimator()
        layoutManager = LinearLayoutManager(context)
    }
}

fun TabLayout.disableClickTablayout(){
    for (i in 0 until this.tabCount){
        (this.getChildAt(0) as ViewGroup).getChildAt(i).isEnabled = false
    }
}

fun createCircularProgressDrawable(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 4f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    return circularProgressDrawable
}

fun createCircularProgressDrawableLight(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.setColorSchemeColors(Color.WHITE)
    circularProgressDrawable.strokeWidth = 4f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    return circularProgressDrawable
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}
fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun convertStringToDate(date: String, time: String): String{
    val dateFromApi = try {
        SimpleDateFormat(Const.DATE_FROM_API, Locale.getDefault()).parse("${date}T${time} GMT")
    }catch (e: Exception){
        SimpleDateFormat(Const.DATE_FROM_API_2, Locale.getDefault()).parse("${date}T${time}")
    }
    return SimpleDateFormat(Const.DATE_MATCH, Locale.getDefault()).format(dateFromApi)
}

fun convertStringToTime(date: String, time: String): String{
    val dateFromApi = try {
        SimpleDateFormat(Const.DATE_FROM_API, Locale.getDefault()).parse("${date}T${time} GMT")
    }catch (e: Exception){
        SimpleDateFormat(Const.DATE_FROM_API_2, Locale.getDefault()).parse("${date}T${time}")
    }
    val timeMatch = SimpleDateFormat(Const.TIME_MATCH, Locale.getDefault()).format(dateFromApi)
    return "Pukul $timeMatch WIB"
}

val Context.database: MyDatabaseOpenHelper get() = MyDatabaseOpenHelper.getInstance(applicationContext)

fun Event.toEventFavourite() = EventFavourite(
    idEvent = this.idEvent,
    strEvent = this.strEvent,
    idHomeTeam = this.idHomeTeam,
    idAwayTeam = this.idAwayTeam,
    dateEvent = this.dateEvent,
    intAwayScore = this.intAwayScore,
    intAwayShots = this.intAwayShots,
    intHomeScore = this.intHomeScore,
    intHomeShots = this.intHomeShots,
    strAwayFormation = this.strAwayFormation,
    strAwayGoalDetails = this.strAwayGoalDetails,
    strAwayLineupDefense = this.strAwayLineupDefense,
    strAwayLineupForward = this.strAwayLineupForward,
    strAwayLineupGoalkeeper = this.strAwayLineupGoalkeeper,
    strAwayLineupMidfield = this.strAwayLineupMidfield,
    strAwayLineupSubstitutes = this.strAwayLineupSubstitutes,
    strAwayRedCards = this.strAwayRedCards,
    strAwayTeam = this.strAwayTeam,
    strAwayYellowCards = this.strAwayYellowCards,
    strHomeFormation = this.strHomeFormation,
    strHomeGoalDetails = this.strHomeGoalDetails,
    strHomeLineupDefense = this.strHomeLineupDefense,
    strHomeLineupForward = this.strHomeLineupForward,
    strHomeLineupGoalkeeper = this.strHomeLineupGoalkeeper,
    strHomeLineupMidfield = this.strHomeLineupMidfield,
    strHomeLineupSubstitutes = this.strHomeLineupSubstitutes,
    strHomeRedCards = this.strHomeRedCards,
    strHomeTeam = this.strHomeTeam,
    strHomeYellowCards = this.strHomeYellowCards,
    strTime = this.strTime
)

fun EventFavourite.toEvent() = Event(
    idEvent = this.idEvent,
    strEvent = this.strEvent,
    idHomeTeam = this.idHomeTeam,
    idAwayTeam = this.idAwayTeam,
    dateEvent = this.dateEvent,
    intAwayScore = this.intAwayScore,
    intAwayShots = this.intAwayShots,
    intHomeScore = this.intHomeScore,
    intHomeShots = this.intHomeShots,
    strAwayFormation = this.strAwayFormation,
    strAwayGoalDetails = this.strAwayGoalDetails,
    strAwayLineupDefense = this.strAwayLineupDefense,
    strAwayLineupForward = this.strAwayLineupForward,
    strAwayLineupGoalkeeper = this.strAwayLineupGoalkeeper,
    strAwayLineupMidfield = this.strAwayLineupMidfield,
    strAwayLineupSubstitutes = this.strAwayLineupSubstitutes,
    strAwayRedCards = this.strAwayRedCards,
    strAwayTeam = this.strAwayTeam,
    strAwayYellowCards = this.strAwayYellowCards,
    strHomeFormation = this.strHomeFormation,
    strHomeGoalDetails = this.strHomeGoalDetails,
    strHomeLineupDefense = this.strHomeLineupDefense,
    strHomeLineupForward = this.strHomeLineupForward,
    strHomeLineupGoalkeeper = this.strHomeLineupGoalkeeper,
    strHomeLineupMidfield = this.strHomeLineupMidfield,
    strHomeLineupSubstitutes = this.strHomeLineupSubstitutes,
    strHomeRedCards = this.strHomeRedCards,
    strHomeTeam = this.strHomeTeam,
    strHomeYellowCards = this.strHomeYellowCards,
    strTime = this.strTime
)