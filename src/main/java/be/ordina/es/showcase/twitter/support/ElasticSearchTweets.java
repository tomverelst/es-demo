package be.ordina.es.showcase.twitter.support;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.social.twitter.api.Tweet;

import java.util.ArrayList;
import java.util.List;

public abstract class ElasticSearchTweets {

    public static List<Tweet> getTweets(SearchResponse response) {
        final List<Tweet> results = new ArrayList<>();

        for(final SearchHit hit : response.getHits()){
            results.add(createTweetfromSearchHit(hit));
        }

        return results;
    }

    private static Tweet createTweetfromSearchHit(final SearchHit searchHit) {
        final TweetSearchHitHelper hit = new TweetSearchHitHelper(searchHit);

        final Tweet tweet = new Tweet(
                Long.valueOf(hit.getId()),
                hit.getString("text"),
                hit.getDate("createdAt"),
                hit.getString("fromUser"),
                hit.getString("profileImageUrl"),
                null,
                hit.getFromUserId(),
                "en",
                null);
        tweet.setFavoriteCount(hit.getInteger("favoriteCount"));
        tweet.setRetweetCount(hit.getInteger("retweetCount"));
        return tweet;
    }
}
