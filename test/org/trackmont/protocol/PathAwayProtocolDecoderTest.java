package org.trackmont.protocol;

import org.junit.Test;
import org.trackmont.ProtocolTest;

public class PathAwayProtocolDecoderTest extends ProtocolTest {

    @Test
    public void testDecode() throws Exception {

        PathAwayProtocolDecoder decoder = new PathAwayProtocolDecoder(new PathAwayProtocol());
        
        verifyPosition(decoder, request(
                "?UserName=name&Password=pass&LOC=$PWS,1,\"Roger\",,,100107,122846,45.317270,-79.642219,45.00,42,1,\"Comment\",0*58"));

    }

}