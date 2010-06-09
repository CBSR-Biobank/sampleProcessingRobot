package edu.ualberta.med.sampleProcessingRobot.serialInterface;

public class RobotException extends Exception {

	/**
	 * The (negative) integer error code that was the reason for this exception
	 */
	// private final int errorCode;

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	/**
	 * These define the integer values for the various errors. These are created
	 * by the X-SEL controller programs based on values in the Integer Constants
	 * table and should not be changed unless they are changed on the controller
	 * as well.
	 */
	public static final int SrcTubeE = -1;
	public static final int PipTipE = -2;
	public static final int CapGrpE = -3;
	public static final int CapStateE = -4;
	public static final int RetSrcE = -5;
	public static final int NoSrcTubE = -6;
	public static final int BrcdeE = -7;
	public static final int SrcGrpE = -8;
	public static final int GetTipE = -9;
	public static final int InvldSrcE = -10;
	public static final int InvldTipE = -11;
	public static final int DspTipE = -12;
	public static final int PipReadE = -13;
	public static final int InvldPltE = -14;
	public static final int InvldCTE = -15;
	public static final int TipTiltE = -16;
	public static final int FldHghtE = -17;
	public static final int LowLevelE = -18;
	public static final int InvldFTAE = -19;

	private static final String[] robotErrors = {
			"Invalid robot error code", // Error 0
			"Error #1: SrcTubeE", "Error #2: PipTipE", "Error #3: CapGrpE",
			"Error #4: CapStateE", "Error #5: RetSrcE", "Error #6: NoSrcTubE",
			"Error #7: BrcdeE", "Error #8: SrcGrpE", "Error #9: GetTipE",
			"Error #10: InvldSrcE", "Error #11: InvldTipE",
			"Error #12: DspTipE", "Error #13: PipReadE",
			"Error #14: InvldPltE", "Error #15: InvldCTE",
			"Error #16: TipTiltE", "Error #17: FldHghtE",
			"Error #18: LowLevelE", "Error #19: InvldFTAE" };

	public RobotException(int err) throws IndexOutOfBoundsException {
		super((err > 0 || err < -robotErrors.length) ? robotErrors[0]
				: robotErrors[-err]);
		// errorCode = err;
	}

	public RobotException(String msg) {
		super(msg);
		// errorCode = 0;
	}
}
