package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides

class GuidesModel {
}



data class Article(

    var name : String?,
    var url :  String?,
    var image : String?
)

data class RelatedBenefit(
    var id : String?,
    var benefit : String?,
    var description : String?
)

data class Guide(
    var ID : Int?,
    var Category : String?,
    var Guide : String?,
    var GuideHeader : String?,
    var Overview : String?,
    var GuideImage : String?,
    var ArticleHeader : String?,
    var Article1Text : String?,
    var Article1URL : String?,
    var Article1Image : String?,
    var Article2Text : String?,
    var Article2URL : String?,
    var Article2Image : String?,
    var Article3Text : String?,
    var Article3URL : String?,
    var Article3Image : String?,
    var Article4Text : String?,
    var Article4URL : String?,
    var Article4Image : String?,
    var MoreArticlesText : String?,
    var MoreArticlesURL : String?,
    var RelatedBenefitsHeader : String?,
    var RelatedBenefits : List<String>?,
    var MoreBenefitsText : String?,
    var MoreBenefitsURL : String?,
    var RelatedWebsitesText : List<String>?,
    var RelatedWebsitesURL : List<String>?,
    var ExpertsText :List<String>?,

    var listArticles : List<Article>?,
    var listRelatedBenefits : List<RelatedBenefit>?,

    var ExpertsHeader : String?
)