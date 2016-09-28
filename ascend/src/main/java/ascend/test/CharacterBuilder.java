package ascend.test;

public class CharacterBuilder {
	public static void main(String[] args) {
		CharacterBuilder cb = new CharacterBuilder();
		try {
			cb.f();
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
		}
	}
	
	public void g() throws NotInterestingException{
		throw new NotInterestingException();
	}
	
	public void f() {
		try{
			g();
		} catch(NotInterestingException nie){
			throw new RuntimeException(nie);
		}
	}
}