# SnsApp
Sns App

## 기술

Kotlin

Dagger, Retrofit, Navigation, Rx, Glide, JUnit, MockK

Repository Pattern, MVVM, DataBinding, Clean Architecture

## 구조

Single Activity, Multi Fragment

MainActivity : FragmentContainer

HomeViewPagerFragment : ViewPagerContainer (Home, PhotoFeed)
HomeFragment : 홈 Tab
PhotoFeedFragment : 사진목록 Tab
CardDetailFragment : 사진 상세 화면
UserDetailFragment : 유저 상세 화면
SignInFragment : 로그인 화면
SignUpFragment : 가입 화면


## Fragment View 구조

* SignInFragment, SignUpFragment 제외

SwipeRefreshLayout
|
|   RecyclerView
|   |
|   |   ViewHolder
|   |   ViewHolder
|   |   ViewHolder
|   |
|   /RecyclerView
|
/SwipeRefreshLayout

SwipeRefreshLayout : 상단 스크롤 시, Refresh
RecyclerView : 레이아웃에 TextView, List, Button 등을 직접 추가하는 것이 아닌 Section 단위 ViewHolder를 추가하는 방식으로 동적인 화면을 구성할 수 있습니다
ViewHolder : Section 단위로 ViewHolder를 구현하여 사용합니다. DataBinding을 통해 화면을 빠르게 구성할 수 있으며 Layout 재사용할 수 있습니다.

## Layout 재사용

구성이 동일한 화면을 재사용합니다.

item_card_image_with_description
    - HomeFragment
        - 인기 카트 PoPularCardViewHolder
        - 인기 유저 PopularUserViewHolder
    - CardDetailFragment
        - 추천 카드 RecommendCardViewHolder

item_vertical_user
    - CardDetailFragment
        - 유저 정보 CardDetailUserDetailViewHolder
    - UserDetailFragment
        - 유저 정보 UserDetailUserViewHolder

## Dagger DI

ApplicationScope, FragmentScope 을 사용합니다.

ApplicationScope : AuthManager 및 network Client 등
FragmentScope : repository, ViewModel

## AuthManager

로그인 유무를 저장합니다.
Singletone 단일 객체로 로그인 상태를 유지하며, LiveData isSignIn을 통해 MainActivity 등에서 로그인 상태를 쉽 업데이트 할 수 있습니다.