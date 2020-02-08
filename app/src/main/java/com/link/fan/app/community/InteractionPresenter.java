package com.link.fan.app.community;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.link.fan.data.bean.Comment;
import com.link.fan.data.bean.CommunityEntity;

import java.util.Date;

public class InteractionPresenter {

    public static final String DATA_FROM_INTERACTION = "data_from_interaction";

    private static final String URL_TOGGLE_FEED_LIK = "/ugc/toggleFeedLike";

    private static final String URL_TOGGLE_FEED_DISS = "/ugc/dissFeed";

    private static final String URL_SHARE = "/ugc/increaseShareCount";

    private static final String URL_TOGGLE_COMMENT_LIKE = "/ugc/toggleCommentLike";

    //给一个帖子点赞/取消点赞，它和给帖子点踩一踩是互斥的
    public static void toggleFeedLike(LifecycleOwner owner, CommunityEntity feed) {
//        if (!isLogin(owner, new Observer<User>() {
//            @Override
//            public void onChanged(User user) {
//                toggleFeedLikeInternal(feed);
//            }
//        })) {
//        } else {
//            toggleFeedLikeInternal(feed);
//        }
    }

    private static void toggleFeedLikeInternal(CommunityEntity feed) {
//        ApiService.get(URL_TOGGLE_FEED_LIK)
//                .addParam("userId", UserManager.get().getUserId())
//                .addParam("itemId", feed.itemId)
//                .execute(new JsonCallback<JSONObject>() {
//                    @Override
//                    public void onSuccess(ApiResponse<JSONObject> response) {
//                        if (response.body != null) {
//                            boolean hasLiked = response.body.getBoolean("hasLiked").booleanValue();
//                            feed.getUgc().setHasLiked(hasLiked);
//                            LiveDataBus.get().with(DATA_FROM_INTERACTION)
//                                    .postValue(feed);
//                        }
//                    }
//
//                    @Override
//                    public void onError(ApiResponse<JSONObject> response) {
//                        showToast(response.message);
//                    }
//                });
    }

    //给一个帖子点踩一踩/取消踩一踩,它和给帖子点赞是互斥的
    public static void toggleFeedDiss(LifecycleOwner owner, CommunityEntity feed) {
//        if (!isLogin(owner, new Observer<User>() {
//            @Override
//            public void onChanged(User user) {
//                toggleFeedDissInternal(feed);
//            }
//        })) {
//        } else {
//            toggleFeedDissInternal(feed);
//        }
    }

    private static void toggleFeedDissInternal(CommunityEntity feed) {
//        ApiService.get(URL_TOGGLE_FEED_DISS).addParam("userId", UserManager.get().getUserId())
//                .addParam("itemId", feed.itemId)
//                .execute(new JsonCallback<JSONObject>() {
//                    @Override
//                    public void onSuccess(ApiResponse<JSONObject> response) {
//                        if (response.body != null) {
//                            boolean hasLiked = response.body.getBoolean("hasLiked").booleanValue();
//                            feed.getUgc().setHasdiss(hasLiked);
//                        }
//                    }
//
//                    @Override
//                    public void onError(ApiResponse<JSONObject> response) {
//                        showToast(response.message);
//                    }
//                });
    }

    //打开分享面板
    public static void openShare(Context context, CommunityEntity feed) {

//        String url = "https://h5.pipix.com/item/%s?app_id=1319&app=super&timestamp=%s&user_id=%s";
//        String format = String.format(url, feed.itemId, new Date().getTime(), UserManager.get().getUserId());
//        ShareDialog shareDialog = new ShareDialog(context);
//        shareDialog.setShareContent(format);
//        shareDialog.setShareItemClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ApiService.get(URL_SHARE)
//                        .addParam("itemId", feed.itemId)
//                        .execute(new JsonCallback<JSONObject>() {
//                            @Override
//                            public void onSuccess(ApiResponse<JSONObject> response) {
//                                if (response.body != null) {
//                                    int count = response.body.getIntValue("count");
//                                    feed.getUgc().setShareCount(count);
//                                }
//                            }
//
//                            @Override
//                            public void onError(ApiResponse<JSONObject> response) {
//                                showToast(response.message);
//                            }
//                        });
//            }
//        });
//
//        shareDialog.show();
    }

    //给一个帖子的评论点赞/取消点赞
    public static void toggleCommentLike(LifecycleOwner owner, Comment comment) {
//        if (!isLogin(owner, new Observer<User>() {
//            @Override
//            public void onChanged(User user) {
//                toggleCommentLikeInternal(comment);
//            }
//        })) {
//        } else {
//            toggleCommentLikeInternal(comment);
//        }
    }

    private static void toggleCommentLikeInternal(Comment comment) {

//        ApiService.get(URL_TOGGLE_COMMENT_LIKE)
//                .addParam("commentId", comment.commentId)
//                .addParam("userId", UserManager.get().getUserId())
//                .execute(new JsonCallback<JSONObject>() {
//                    @Override
//                    public void onSuccess(ApiResponse<JSONObject> response) {
//                        if (response.body != null) {
//                            boolean hasLiked = response.body.getBooleanValue("hasLiked");
//                            comment.getUgc().setHasLiked(hasLiked);
//                        }
//                    }
//
//                    @Override
//                    public void onError(ApiResponse<JSONObject> response) {
//                        showToast(response.message);
//                    }
//                });
    }

    //收藏/取消收藏一个帖子
    public static void toggleFeedFavorite(LifecycleOwner owner, CommunityEntity feed) {
//        if (!isLogin(owner, new Observer<User>() {
//            @Override
//            public void onChanged(User user) {
//                toggleFeedFavorite(feed);
//            }
//        })) {
//        } else {
//            toggleFeedFavorite(feed);
//        }
    }

    private static void toggleFeedFavorite(CommunityEntity feed) {
//        ApiService.get("/ugc/toggleFavorite")
//                .addParam("itemId", feed.itemId)
//                .addParam("userId", UserManager.get().getUserId())
//                .execute(new JsonCallback<JSONObject>() {
//                    @Override
//                    public void onSuccess(ApiResponse<JSONObject> response) {
//                        if (response.body != null) {
//                            boolean hasFavorite = response.body.getBooleanValue("hasFavorite");
//                            feed.getUgc().setHasFavorite(hasFavorite);
//                            LiveDataBus.get().with(DATA_FROM_INTERACTION)
//                                    .postValue(feed);
//                        }
//                    }
//
//                    @Override
//                    public void onError(ApiResponse<JSONObject> response) {
//                        showToast(response.message);
//                    }
//                });
    }


    //关注/取消关注一个用户
    public static void toggleFollowUser(LifecycleOwner owner, CommunityEntity feed) {
//        if (!isLogin(owner, new Observer<User>() {
//            @Override
//            public void onChanged(User user) {
//                toggleFollowUser(feed);
//            }
//        })) {
//        } else {
//            toggleFollowUser(feed);
//        }
    }

    private static void toggleFollowUser(CommunityEntity feed) {
//        ApiService.get("/ugc/toggleUserFollow")
//                .addParam("followUserId", UserManager.get().getUserId())
//                .addParam("userId", feed.author.userId)
//                .execute(new JsonCallback<JSONObject>() {
//                    @Override
//                    public void onSuccess(ApiResponse<JSONObject> response) {
//                        if (response.body != null) {
//                            boolean hasFollow = response.body.getBooleanValue("hasLiked");
//                            feed.getAuthor().setHasFollow(hasFollow);
//                            LiveDataBus.get().with(DATA_FROM_INTERACTION)
//                                    .postValue(feed);
//                        }
//                    }
//
//                    @Override
//                    public void onError(ApiResponse<JSONObject> response) {
//                        showToast(response.message);
//                    }
//                });
    }

    public static LiveData<Boolean> deleteFeed(Context context, long itemId) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        new AlertDialog.Builder(context)
                .setNegativeButton("删除", (dialog, which) -> {
                    dialog.dismiss();
                    deleteFeedInternal(liveData, itemId);
                }).setPositiveButton("取消", (dialog, which) -> dialog.dismiss()).setMessage("确定要删除这条评论吗？").create().show();
        return liveData;
    }

    private static void deleteFeedInternal(MutableLiveData<Boolean> liveData, long itemId) {
//        ApiService.get("/feeds/deleteFeed")
//                .addParam("itemId", itemId)
//                .execute(new JsonCallback<JSONObject>() {
//                    @Override
//                    public void onSuccess(ApiResponse<JSONObject> response) {
//                        if (response.body != null) {
//                            boolean success = response.body.getBoolean("result");
//                            liveData.postValue(success);
//                            showToast("删除成功");
//                        }
//                    }
//
//                    @Override
//                    public void onError(ApiResponse<JSONObject> response) {
//                        showToast(response.message);
//                    }
//                });
    }

    //删除某个帖子的一个评论
    public static LiveData<Boolean> deleteFeedComment(Context context, long itemId, long commentId) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        new AlertDialog.Builder(context)
                .setNegativeButton("删除", (dialog, which) -> {
                    dialog.dismiss();
                    deleteFeedCommentInternal(liveData, itemId, commentId);
                }).setPositiveButton("取消", (dialog, which) -> dialog.dismiss()).setMessage("确定要删除这条评论吗？").create().show();
        return liveData;
    }

    private static void deleteFeedCommentInternal(LiveData liveData, long itemId, long commentId) {
//        ApiService.get("/comment/deleteComment")
//                .addParam("userId", UserManager.get().getUserId())
//                .addParam("commentId", commentId)
//                .addParam("itemId", itemId)
//                .execute(new JsonCallback<JSONObject>() {
//                    @Override
//                    public void onSuccess(ApiResponse<JSONObject> response) {
//                        if (response.body != null) {
//                            boolean result = response.body.getBooleanValue("result");
//                            ((MutableLiveData) liveData).postValue(result);
//                            showToast("评论删除成功");
//                        }
//                    }
//
//                    @Override
//                    public void onError(ApiResponse<JSONObject> response) {
//                        showToast(response.message);
//                    }
//                });
    }


    //关注/取消关注一个帖子标签
//    public static void toggleTagLike(LifecycleOwner owner, TagList tagList) {
//        if (!isLogin(owner, new Observer<User>() {
//            @Override
//            public void onChanged(User user) {
//                toggleTagLikeInternal(tagList);
//            }
//        })) ;
//        else {
//            toggleTagLikeInternal(tagList);
//        }
//    }
//
//    private static void toggleTagLikeInternal(TagList tagList) {
//        ApiService.get("/tag/toggleTagFollow")
//                .addParam("tagId", tagList.tagId)
//                .addParam("userId", UserManager.get().getUserId())
//                .execute(new JsonCallback<JSONObject>() {
//                    @Override
//                    public void onSuccess(ApiResponse<JSONObject> response) {
//                        if (response.body != null) {
//                            Boolean follow = response.body.getBoolean("hasFollow");
//                            tagList.setHasFollow(follow);
//                        }
//                    }
//
//                    @Override
//                    public void onError(ApiResponse<JSONObject> response) {
//                        showToast(response.message);
//                    }
//                });
//    }
//
//    private static void showToast(String message) {
//        ArchTaskExecutor.getMainThreadExecutor().execute(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(AppGlobals.getApplication(), message, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private static boolean isLogin(LifecycleOwner owner, Observer<User> observer) {
//        if (UserManager.get().isLogin()) {
//            return true;
//        } else {
//            LiveData<User> liveData = UserManager.get().login(AppGlobals.getApplication());
//            if (owner == null) {
//                liveData.observeForever(loginObserver(observer, liveData));
//            } else {
//                liveData.observe(owner, loginObserver(observer, liveData));
//            }
//            return false;
//        }
//    }
//
//    @NotNull
//    private static Observer<User> loginObserver(Observer<User> observer, LiveData<User> liveData) {
//        return new Observer<User>() {
//            @Override
//            public void onChanged(User user) {
//                liveData.removeObserver(this);
//                if (user != null && observer != null) {
//                    observer.onChanged(user);
//                }
//            }
//        };
//    }

}
