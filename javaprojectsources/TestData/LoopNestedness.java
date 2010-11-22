
public class LoopNestedness {

	public void methodOne() {
		while(true){
			do {
				// blubb
			} while(true);
		}
	}
	
	public void methodTwo() {
		while(true) {
			while(true) {
				// blubb
			}
			while(true) {
				for(int i = 0; i < 1; i++){
					// blabb
				}
			}		
		}
	}
}
