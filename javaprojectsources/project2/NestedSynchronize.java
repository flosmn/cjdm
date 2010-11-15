public class NestedSynchronize{

	public void foo(){
		synchronized(this){
			eat();
			synchronized(this){
				eat();
				eat();
			}		
		}
	}
	
	public void bar(){
		synchronized(this){
			eat();
			synchronized(this){
				eat();
				synchronized(this){
					eat();
					synchronized(this){
						eat();
						eat();
					}	
				}	
			}		
		}
	}
	
	private void eat() {
	}

}
