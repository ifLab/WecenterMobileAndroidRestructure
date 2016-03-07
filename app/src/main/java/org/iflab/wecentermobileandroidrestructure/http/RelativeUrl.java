package org.iflab.wecentermobileandroidrestructure.http;

import org.iflab.wecentermobileandroidrestructure.tools.MD5Transform;

/**
 * Created by hcjcch on 15/5/17.
 */
public class RelativeUrl {
    public static String USER_LOGIN = "/api/account/login_process/" + MD5Transform.getSign("account"); //登录
    public static String HOME_PAGE = "/api/home/" + MD5Transform.getSign("home"); //首页
    public static String USER_INFO = "/api/account/get_userinfo/";//用户信息
    public static String USER_IMG_EDIT = "/api/account/avatar_upload/" + MD5Transform.getSign("account");//用户头像修改
    public static String USER_INFORMATION_EDIT = "/api/people/profile_setting/" + MD5Transform.getSign("people");//用户信息修改
    public static String USER_INFO_GET_EDIT = "api/profile.php" + MD5Transform.getSign("profile");//用户信息修改前的获取信息
    public static String ATTACHMENT_UPLOAD = "api/publish/attach_upload/" + MD5Transform.getSign("publish");//上传附件
    public static String UPLOAD_QUESTION = "?/api/publish/publish_question/" + MD5Transform.getSign("publish");//上传问题
    public static String FOUND = "?/api/explore/" + MD5Transform.getSign("explore");//发现
//    public static String PERSONAL_ARTICLE = "api/my_article.php" + MD5Transform.getSign("my_article");//个人文章
    public static String USER_ACTION = "/api/people/user_actions/" ;
    public static String TOPIC_BEST_ANSWER = "/api/topic/topic_best_answer_list/";
    public static String TOPIC_DETAIL = "/api/topic/topic/";
    public static String TOPIC_FOCUS = "/topic/ajax/focus_topic/";
    public static String UPLOAD_ANSWER = "/api/question/save_answer_comment/" + MD5Transform.getSign("question");//发布回答评论
//    public static String PERSONAL_ANSWER = "api/my_answer.php" + MD5Transform.getSign("my_answer");//个人回答
    public static String PERSONAL_TOPIC = "/api/people/topics/";//个人话题
    public static String TOPICS = "/api/topic/topics/" + MD5Transform.getSign("topic");
    public static String FOLLOWER = "api/people/follows/";//个人粉丝
    public static String ARTICLE_INFO = "/api/article/" ; //文章
    public static String QUESTION_INFO = "/api/question/" + MD5Transform.getSign("question");//问题
    public static String QUESTION_FOUCS = "/question/ajax/focus/" + MD5Transform.getSign("ajax");//关注问题
    public static String QUESTION_ANSWER_INFO = "/api/question/answer/";
    public static String ANSWER_VOTE = "/question/ajax/answer_vote/" + MD5Transform.getSign("ajax");
    public static String ARTICLE_VOTE = "/article/ajax/article_vote/" + MD5Transform.getSign("ajax");
    public static String ANSWER_COMMENT = "api/question/answer_comments/" + MD5Transform.getSign("question");
    public static String ARTICLE_COMMENT = "/api/article/article_comments/";
    public static String PUBLISH_ARTICLE = "/api/publish/publish_article/" + MD5Transform.getSign("publish");
    public static String PUBLISH_ANSWER = "/api/publish/save_answer/" + MD5Transform.getSign("publish");//回答问题
    public static String SEARCH = "/api/search/" + MD5Transform.getSign("search");  //搜索
    public static String FOLLOW_PEOPLE = "/follow/ajax/follow_people/" + MD5Transform.getSign("ajax");
    public static String REGISTER = "api/account/register_process/" + MD5Transform.getSign("account");
    public static String HOT_TOPICS = "/api/topic/hot_topics/";
    public static String CRASH_LOG = "/api/log/crash/" + MD5Transform.getSign("log");
}