
package cn.featherfly.common.mqtt;

import java.util.function.BiPredicate;

/**
 * <p>
 * TopicMatcher
 * </p>
 *
 * @author zhongj
 */
public class TopicMatcher implements BiPredicate<String, String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(String topic, String subscribeTopicFilter) {
        // TODO 后续来做mqtt topic 匹配模式
        return topic.equals(subscribeTopicFilter);
    }

}
