/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.ordina.es.showcase.twitter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
public class TwitterSearchController {

    private final TweetRepository repository;

	@Inject
	public TwitterSearchController(TweetRepository repository) {
        this.repository = repository;
	}

    @RequestMapping(value="/twitter/search", method= RequestMethod.GET)
    public String showSearch() {
        return "twitter/search";
    }

	@RequestMapping(value="/twitter/search", method= RequestMethod.POST)
	public String search(@RequestParam("query") String query, Model model) {
        model.addAttribute("tweets", repository.search(query));
		return "twitter/search";
	}

}
