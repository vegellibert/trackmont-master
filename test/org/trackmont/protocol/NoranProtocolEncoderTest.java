package org.trackmont.protocol;

import org.junit.Test;
import org.trackmont.ProtocolTest;
import org.trackmont.model.Command;

public class NoranProtocolEncoderTest extends ProtocolTest {

    @Test
    public void testEncode() throws Exception {

        NoranProtocolEncoder encoder = new NoranProtocolEncoder();
        
        Command command = new Command();
        command.setDeviceId(1);
        command.setType(Command.TYPE_ENGINE_STOP);

        verifyCommand(encoder, command, binary(
                "0d0a2a4b5700440002000000000000002a4b572c3030302c3030372c3030303030302c302300000000000000000000000000000000000000000000000000000000000d0a"));

    }

}
