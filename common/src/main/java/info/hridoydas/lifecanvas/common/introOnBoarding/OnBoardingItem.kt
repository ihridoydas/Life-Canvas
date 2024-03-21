package info.hridoydas.lifecanvas.common.introOnBoarding

import info.hridoydas.lifecanvas.common.R


class OnBoardingItem(
    val title: Int,
    val text:Int,
    val image:Int,
) {

    companion object{

        fun get():List<OnBoardingItem>{
            return listOf(
                OnBoardingItem(R.string.intro_one_title,R.string.intro_one_description,R.drawable.one),
                OnBoardingItem(R.string.intro_two_title,R.string.intro_two_description,R.drawable.two),
                OnBoardingItem(R.string.intro_three_title,R.string.intro_three_description,R.drawable.three),
            )
        }

    }

}
