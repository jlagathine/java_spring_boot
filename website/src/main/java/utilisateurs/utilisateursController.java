package utilisateurs;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping (path = "api/v1/userDatas")
public class utilisateursController {
	
	@GetMapping
	public List<UserDatas> getUsers() {
		return List.of(new UserDatas(1L, "REGIS", "Laure", "laure@mail.com", LocalDate.of(1985, Month.DECEMBER, 1), 36));
	}

}

