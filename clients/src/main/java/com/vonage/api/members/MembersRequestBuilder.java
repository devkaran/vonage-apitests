package com.vonage.api.members;

import com.beust.ah.A;
import com.vonage.api.members.request.CreateMemberRequest;
import com.vonage.api.users.UsersRequestBuilder;
import com.vonage.api.users.request.UpdateUserRequest;
import lombok.experimental.UtilityClass;
import org.testng.collections.Lists;

@UtilityClass
public class MembersRequestBuilder {

    public CreateMemberRequest buildCreateMemberRequestBuilder(String userId, String memberId, String legId){
        return CreateMemberRequest.builder()
                .action("join")
                .userId(userId)
                .memberId(memberId)
                .channel(buildChannel(legId))
                .media(CreateMemberRequest.Media.builder().audioSettings(CreateMemberRequest.Media.AudioSettings.builder()
                                .enabled(false)
                                .earmuffed(false)
                                .muted(false)
                        .build()).build())
//                .knockingId(knockingId) Knocker ID. A knocker is a pre-member of a conversation who does not exist yet
                .knockingId("a972836a-450f-35fa-156c-52a2ab5b7d25")
                .memberIdInviting(memberId)
                .build();
    }

    public CreateMemberRequest.Channel buildChannel(String legId){
        return CreateMemberRequest.Channel.builder()
                .type("phone")
                .legId(legId)
                .from(CreateMemberRequest.Channel.From.builder().type("app").user("jamie").build())
                .to(CreateMemberRequest.Channel.To.builder().type("app").user("jamie").build())
                .legIds(Lists.newArrayList(UpdateUserRequest.Channels.LegIds.builder().legId(legId).build()))
                .build();
    }

    public CreateMemberRequest updateMemberRequestBuilder(String legId){
        return CreateMemberRequest.builder()
                .action("join")
                .channel(buildChannel(legId))
                .build();

    }
}
