package org.trackmont.protocol;

import org.junit.Test;
import org.trackmont.ProtocolTest;

public class EskyProtocolDecoderTest extends ProtocolTest {

    @Test
    public void testDecode() throws Exception {

        EskyProtocolDecoder decoder = new EskyProtocolDecoder(new EskyProtocol());

        verifyNull(decoder, text(
                "EL;1;864906029196626;170822143426;"));

        verifyPosition(decoder, text(
                "EO;0;864906029196626;R;7+170822143646+-26.10806+27.94600+0.40+0+0x1+0+102540+0+1242"));

        verifyPosition(decoder, text(
                "EO;0;864906029196626;R;0+170808155352+0.00000+0.00000+0.00+0+0x1+0+0+0+1233"));

    }

}
