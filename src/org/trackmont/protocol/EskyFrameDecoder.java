/*
 * Copyright 2017 Vicente Venegas (vicente@republik.ec)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.trackmont.protocol;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class EskyFrameDecoder extends FrameDecoder {

    @Override
    protected Object decode(
            ChannelHandlerContext ctx, Channel channel, ChannelBuffer buf) throws Exception {

        buf.readerIndex(buf.indexOf(buf.readerIndex(), buf.writerIndex(), (byte) 'E'));

        int endIndex = buf.indexOf(buf.readerIndex() + 1, buf.writerIndex(), (byte) 'E');
        if (endIndex > 0) {
            return buf.readBytes(endIndex - buf.readerIndex());
        } else {
            return buf.readBytes(buf.readableBytes()); // assume full frame
        }
    }

}
