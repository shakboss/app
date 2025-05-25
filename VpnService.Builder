// After successful auth and receiving DNS info from your server:
// String vpnClientIp = "10.99.0.2"; // From server auth response
// String dnsServerIp = "10.99.0.1"; // From server auth response
// int prefixLength = 24; // Usually 24 for /24, or from server

builder.addAddress(vpnClientIp, prefixLength);
builder.addDnsServer(dnsServerIp);
builder.addRoute("0.0.0.0", 0); // Route all traffic through VPN
// Potentially add specific routes if needed
// builder.addRoute("10.99.0.0", 16); // Route for VPN subnet itself

// ... then call builder.establish()
