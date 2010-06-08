/**
 * 
 */
package edu.ualberta.med.sampleProcessingRobot.serialInterface;

import gnu.io.SerialPort;

/**
 * @author Mike Sokolsky
 * 
 */
public class BarcodeInterface {

	public SerialInterface port;

	static final byte[] BARCODE_START_READ = { 0x31, 0x42, 0x30, 0x32, 0x35,
			0x33, 0x30, 0x44, 0x30, 0x41 };

	// static final byte[] BARCODE_START_READ = {
	// 0x1B, 0x02, 0x53, 0x0D, 0x0A };
	/**
	 * Creates a new BarcodeInterface connected to the specified port
	 * 
	 * @param portName
	 *            Name of the COM port to connect to the Barcode reader on
	 */
	public BarcodeInterface(String portName) {
		port = new SerialInterface(portName, 9600, SerialPort.DATABITS_7,
				SerialPort.STOPBITS_2, SerialPort.PARITY_EVEN);
	}

	/**
	 * Signals the barcode reader to start scanning for a barcode
	 * 
	 * @return the value returned by the barcode reader. This should either be
	 *         the barcode string or NOREAD if it timed out.
	 */
	public String readCode() {

		String response = "";

		port.write(BARCODE_START_READ);

		// Why?!?
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		// Probably don't need to do this but don't touch it for now.
		final class ReadCode extends Thread {

			private String response;

			public synchronized String getResponse() {
				return response;
			}

			@Override
			public void run() {

				String tempResponse = "";

				byte buffer[] = new byte[100];
				int len = -1;
				while (!tempResponse.contains("\r")) {
					len = port.read(buffer);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
					;

					if (len > 0) {
						tempResponse += new String(buffer, 0, len);
						// System.out.println("Got " + len + " bytes" );
					}
				}
				if (tempResponse.length() > 0)
					// Strip off the leading STA and trailing CR
					response = tempResponse.substring(1,
							tempResponse.length() - 1);
			}
		}

		ReadCode reading = new ReadCode();
		reading.start();
		while ((response = reading.getResponse()) == null)
			;
		System.out.println("Read =" + response + "=");
		return response;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BarcodeInterface barcodeReader = new BarcodeInterface("COM3");
		if (!barcodeReader.port.isOpen())
			System.exit(1);
		while (true) {
			barcodeReader.readCode();
		}
	}

}
