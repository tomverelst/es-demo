package be.ordina.es.showcase.twitter.support;

import be.ordina.es.showcase.twitter.TweetRepository;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.base.Splitter;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.social.twitter.api.Tweet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ElasticSearchTweetRepository implements TweetRepository, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchTweetRepository.class);

    private static final String INDEX_NAME = "twitter";

    private static final String TYPE_NAME = "tweet";

    private static final Splitter QUERY_SPLITTER = Splitter.on(' ').omitEmptyStrings().trimResults();

    private Client client;

    public ElasticSearchTweetRepository(Settings settings){
        this.client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
    }

    @Override
    public void save(Tweet tweet) {
        Map<String, Object> json = new HashMap<>();
        json.put("fromUser",tweet.getFromUser());
        json.put("fromUserId", tweet.getFromUserId());
        json.put("createdAt", tweet.getCreatedAt());
        json.put("text", tweet.getText());
        json.put("favoriteCount", tweet.getFavoriteCount());
        json.put("retweetCount", tweet.getRetweetCount());
        json.put("profileImageUrl", tweet.getProfileImageUrl());

        // TODO Create and execute the index operation
        IndexResponse response = null; //client.prepareIndex..

        logger.info("Saved tweet in ElasticSearch in index {}", response.getIndex());
    }

    @Override
    public List<Tweet> search(final String query) {

        final List<String> terms = getTerms(query);

        // TODO Create and execute the search operation
        final SearchResponse response = null; //client.prepareSearch...

        return ElasticSearchTweets.getTweets(response);
    }

    @Override
    public void destroy() throws Exception {
        client.close();
    }

    private static List<String> getTerms(String query) {
        return QUERY_SPLITTER.splitToList(query)
                .stream().map((term) -> term.toLowerCase()).collect(Collectors.toList());
    }
}
