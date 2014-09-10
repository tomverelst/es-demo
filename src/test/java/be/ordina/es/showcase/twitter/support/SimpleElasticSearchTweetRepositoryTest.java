package be.ordina.es.showcase.twitter.support;

import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.junit.Before;
import org.junit.Test;
import org.springframework.social.twitter.api.Tweet;

import java.util.Date;
import java.util.List;

public class SimpleElasticSearchTweetRepositoryTest {

    private ElasticSearchTweetRepository repository;

    @Before
    public void setUp(){
        final Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "elasticsearch_tomverelst")
                .put("node.name", "Connectivator")
                .build();
       repository = new ElasticSearchTweetRepository(settings);
    }

    @Test
    public void testSaveTweet(){
        repository.save(tweet());
    }

    @Test
    public void testSearch(){
        final List<Tweet> tweets = repository.search("ordina");

        tweets.forEach((tweet) -> System.out.println(tweet));
    }

    private Tweet tweet(){
        return new Tweet(12345678L,
         "This is a test tweet",
         new Date(),
         "tomverelst",
         "https://pbs.twimg.com/profile_images/3318563611/f3af0d1c43142f5b6d24d5af797e8f64_bigger.png",
         1234L,
         4321L,
         "en",
        "");
    }

}