Intent intent = new Intent(this, MyVpnService.class);
intent.putExtra("SERVER_ADDRESS", serverIp);
// ... put other params
startService(intent);
