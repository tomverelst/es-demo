package be.ordina.es.showcase.twitter.support;

import org.elasticsearch.common.joda.time.format.ISODateTimeFormat;
import org.elasticsearch.search.SearchHit;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

class TweetSearchHitHelper {

    private final SearchHit hit;

    private final Map<String,Object> source;

    TweetSearchHitHelper(final SearchHit searchHit) {
        this.hit = Objects.requireNonNull(searchHit);
        this.source = Objects.requireNonNull(searchHit.sourceAsMap());
    }

    public long getFromUserId(){
        final Long fromUserId = getLong("fromUserId");
        if(fromUserId == null){
            return 0L;
        }
        return fromUserId;
    }

    public String getId(){
        return hit.getId();
    }

    private Object get(final String key){
        return source.get(key);
    }

    public String getString(final String key){
        Object field = get(key);
        if(field == null){
            return null;
        }
        return (String)field;
    }

    public Integer getInteger(final String key){
        Object field = get(key);
        if(field == null){
            return null;
        }
        return (Integer)field;
    }

    public Long getLong(final String key){
        Object field = get(key);
        if(field == null){
            return null;
        }

        if(field instanceof Integer){
            return ((Integer)field).longValue();
        }

        return (Long)field;
    }

    public Date getDate(final String key){
        Object field = get(key);
        if(field != null){
            return ISODateTimeFormat.dateOptionalTimeParser().parseDateTime((String)field).toDate();
        } else {
            return null;
        }
    }

}
