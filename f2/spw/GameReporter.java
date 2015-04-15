package f2.spw;

public interface GameReporter {
	boolean bossNow();
	boolean drawBossStage();
	int getNumLife();
	int getNumBossLife();
	long getScore();

}
