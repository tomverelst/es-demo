package be.ordina.es.showcase.twitter;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

/**
 * @author Tom Verelst
 */
@Controller
public class TwitterIndexController {

    private Twitter twitter;

    private TweetRepository repository;

    @Inject
    public TwitterIndexController(Twitter twitter, TweetRepository repository){
        this.twitter = twitter;
        this.repository = repository;
    }


    @RequestMapping(value = "/twitter/index", method= RequestMethod.GET)
    public String view(){
        return "twitter/index";
    }

    @RequestMapping(value = "/twitter/index", method= RequestMethod.POST)
    public String index(@RequestParam("query") String query){
        twitter.searchOperations().search(query).getTweets()
                .forEach((tweet) -> repository.save(tweet));
        return "twitter/index";
    }

}
