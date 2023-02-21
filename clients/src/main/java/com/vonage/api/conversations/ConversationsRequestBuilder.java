package com.vonage.api.conversations;

import com.beust.jcommander.internal.Lists;
import com.vonage.api.conversations.request.ConversationRequest;
import com.vonage.api.conversations.request.RecordingRequest;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

@UtilityClass
public class ConversationsRequestBuilder {

    public ConversationRequest bulidConversationRequest() {
        String randStr = RandomStringUtils.randomAlphabetic(5);
        return ConversationRequest.builder()
                .name("customer_chat "+randStr)
                .displayName("Customer Chat "+randStr)
                .imageUrl("https://example.com/image.png")
                .properties(ConversationRequest.Properties.builder().ttl(3600).build())
                .build();
    }


    public RecordingRequest bulidRecordingRequest() {
        return RecordingRequest.builder()
                .action("start")
                .eventUrl(Lists.newArrayList("https://example.com/event"))
                .eventMethod("POST")
                .split("conversation")
                .format("mp3")
                .build();
    }
}
