/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.bot.model.event;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import com.linecorp.bot.model.event.source.Source;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Event object for when your account is added as a friend (or unblocked). You can reply to follow events.
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonTypeName("follow")
@JsonDeserialize(builder = FollowEvent.FollowEventBuilder.class)
public class FollowEvent implements Event, ReplyEvent {
    /**
     * Token for replying to this event.
     */
    private final String replyToken;

    /**
     * JSON object which contains the source of the event.
     */
    private final Source source;

    /**
     * Time of the event.
     */
    private final Instant timestamp;

    /**
     * Channel state.
     * <dl>
     * <dt>active</dt>
     * <dd>The channel is active. You can send a reply message or push message from the bot server that received
     * this webhook event.</dd>
     * <dt>standby (under development)</dt>
     * <dd>The channel is waiting. The bot server that received this webhook event shouldn't send any messages.
     * </dd>
     * </dl>
     */
    private EventMode mode;

    /**
     * Deprecated constructor.
     *
     * @deprecated Use builder method instead. This construct will remove in next major release.
     */
    @Deprecated
    public FollowEvent(
            @JsonProperty("replyToken") final String replyToken,
            @JsonProperty("source") final Source source,
            @JsonProperty("timestamp") final Instant timestamp) {
        this(replyToken, source, timestamp, null);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class FollowEventBuilder {
        // Filled by lombok
    }
}
