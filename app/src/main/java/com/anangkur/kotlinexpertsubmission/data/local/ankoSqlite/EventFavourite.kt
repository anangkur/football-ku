import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventFavourite(
    val idEvent: String? = "",
    val intHomeScore: String? = "",
    val intAwayScore: String? = "",
    val strHomeTeam: String? = "",
    val strAwayTeam: String? = "",
    val strTime: String? = "",
    val dateEvent: String? = "",
    val strHomeGoalDetails: String? = "",
    val strAwayGoalDetails: String? = "",
    val intHomeShots: Int = 0,
    val intAwayShots: Int = 0,
    val strHomeFormation: String? = "",
    val strAwayFormation: String? = "",
    val strHomeYellowCards: String? = "",
    val strAwayYellowCards: String? = "",
    val strHomeRedCards: String? = "",
    val strAwayRedCards: String? = "",
    val strHomeLineupGoalkeeper: String? = "",
    val strHomeLineupDefense: String? = "",
    val strHomeLineupMidfield: String? = "",
    val strHomeLineupForward: String? = "",
    val strHomeLineupSubstitutes: String? = "",
    val strAwayLineupGoalkeeper: String? = "",
    val strAwayLineupDefense: String? = "",
    val strAwayLineupMidfield: String? = "",
    val strAwayLineupForward: String? = "",
    val strAwayLineupSubstitutes: String? = ""
): Parcelable{
    companion object{
        const val TABLE_EVENT = "TABLE_EVENT"
        const val COLUMN_ID = "ID"
        const val COLUMN_HOME_SCORE = "HOME_SCORE"
        const val COLUMN_AWAY_SCORE = "AWAY_SCORE"
        const val COLUMN_HOME_TEAM = "HOME_TEAM"
        const val COLUMN_AWAY_TEAM = "AWAY_TEAM"
        const val COLUMN_TIME = "TIME"
        const val COLUMN_DATE = "DATE"
        const val COLUMN_HOME_GOAL = "HOME_GOAL"
        const val COLUMN_AWAY_GOAL = "AWAY_GOAL"
        const val COLUMN_HOME_SHOOT = "HOME_SHOOT"
        const val COLUMN_AWAY_SHOOT = "AWAY_SHOOT"
        const val COLUMN_HOME_FORMATION = "HOME_FORMATION"
        const val COLUMN_AWAY_FORMATION = "AWAY_FORMATION"
        const val COLUMN_HOME_YELLOW = "HOME_YELLOW"
        const val COLUMN_AWAY_YELLOW = "AWAY_YELLOW"
        const val COLUMN_HOME_RED = "HOME_RED"
        const val COLUMN_AWAY_RED = "AWAY_RED"
        const val COLUMN_HOME_KEEPER = "HOME_KEEPER"
        const val COLUMN_AWAY_KEEPER = "AWAY_KEEPER"
        const val COLUMN_HOME_DEFENSE = "HOME_DEFENSE"
        const val COLUMN_AWAY_DEFENSE = "AWAY_DEFENSE"
        const val COLUMN_HOME_MIDFIELD = "HOME_MIDFIELD"
        const val COLUMN_AWAY_MIDFIELD = "AWAY_MIDFIELD"
        const val COLUMN_HOME_FORWARD = "HOME_FORWARD"
        const val COLUMN_AWAY_FORWARD = "AWAY_FORWARD"
        const val COLUMN_HOME_SUBTITUTE = "HOME_SUBTITUTE"
        const val COLUMN_AWAY_SUBTITUTE = "AWAY_SUBTITUTE"
    }
}