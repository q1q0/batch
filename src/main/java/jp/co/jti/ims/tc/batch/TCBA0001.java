package jp.co.jti.ims.tc.batch;

import java.util.List;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TCBA0001 {
	@Autowired
	private TestRepo repo;

	public void main(String[] args) {	
		System.out.println(args[0]);
		List<HashMap> oj = this.repo.testSelect(args[0]);
		
		System.out.println(oj);	
		System.out.println(oj.get(0).get("FIRST_NAME"));

	}
}
