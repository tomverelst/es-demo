package be.ordina.es.showcase.twitter;

import org.springframework.social.twitter.api.Tweet;

import java.util.List;

/**
 * @author Tom Verelst
 */
public interface TweetRepository {

    void save(Tweet tweet);

    List<Tweet> search(String query);

}
