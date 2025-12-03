package net.minecraft.src;

class NetworkReaderThread extends Thread {
	final NetworkManager netManager;

	NetworkReaderThread(NetworkManager var1, String var2) {
		super(var2);
		this.netManager = var1;
	}

	public void run() {
		Object var1 = NetworkManager.threadSyncObject;
		synchronized(var1) {
			++NetworkManager.numReadThreads;
		}

		while(true) {
			boolean var12 = false;

			try {
				var12 = true;
				if(NetworkManager.isRunning(this.netManager)) {
					if(!NetworkManager.isServerTerminating(this.netManager)) {
						NetworkManager.readNetworkPacket(this.netManager);

						try {
							sleep(0L);
						} catch (InterruptedException var15) {
						}
						continue;
					}

					var12 = false;
					break;
				}

				var12 = false;
				break;
			} finally {
				if(var12) {
					Object var5 = NetworkManager.threadSyncObject;
					synchronized(var5) {
						--NetworkManager.numReadThreads;
					}
				}
			}
		}

		var1 = NetworkManager.threadSyncObject;
		synchronized(var1) {
			--NetworkManager.numReadThreads;
		}
	}
}
