// Example for Auth Request
public static byte[] buildAuthPacket(byte senderCounter, String username, String password) {
    byte[] userBytes = username.getBytes(StandardCharsets.UTF_8);
    byte[] passBytes = password.getBytes(StandardCharsets.UTF_8);

    if (userBytes.length > 255 || passBytes.length > 255) {
        throw new IllegalArgumentException("Username/password too long");
    }

    byte ul = (byte) userBytes.length;
    byte pl = (byte) passBytes.length;

    // LL = 1 (UL) + userBytes.length + 1 (PL) + passBytes.length
    int payloadLenVal = 1 + userBytes.length + 1 + passBytes.length;
    ByteBuffer ll_buffer = ByteBuffer.allocate(2);
    ll_buffer.putShort((short) payloadLenVal); // Big Endian by default

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(0x01);                 // Packet Type
    baos.write(senderCounter);        // Sender Counter
    baos.write(ll_buffer.array(), 0, 2); // LL (Payload Length)
    baos.write(ul);                   // UL
    baos.write(userBytes, 0, userBytes.length); // USERNAME
    baos.write(pl);                   // PL
    baos.write(passBytes, 0, passBytes.length); // PASSWORD
    return baos.toByteArray();
}

// Example for Data Packet
public static byte[] buildDataPacket(byte senderCounter, byte[] ipPacket) {
    ByteBuffer ll_buffer = ByteBuffer.allocate(2);
    ll_buffer.putShort((short) ipPacket.length); // Big Endian for IP packet length

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(0x03);                // Packet Type
    baos.write(senderCounter);       // Sender Counter
    baos.write(ll_buffer.array(), 0, 2); // LL (IP Packet Length)
    baos.write(ipPacket, 0, ipPacket.length); // PAYLOAD (Raw IP Packet)
    return baos.toByteArray();
}

// You'll also need methods to parse responses from the server.
// e.g., parseAuthResponse, parseDataPacket
