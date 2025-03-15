package hse.kpo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes= ZooApplication.class)
class ZooApplicationTests {

	@Autowired
	private ZooService zooService;
	@Autowired
	private VetClinic vetClinic;
	@Autowired
	private AnimalStorage animalStorage;
	@Autowired
	private ThingStorage thingStorage;
	@Test
	@DisplayName("Тест загрузки контекста")
	void contextLoads() {
		Assertions.assertNotNull(zooService);
		Assertions.assertNotNull(vetClinic);
		Assertions.assertNotNull(animalStorage);
		Assertions.assertNotNull(thingStorage);
	}

	@Test
	@DisplayName("Тест загрузки контекста")
	void hseCarServiceTest() {
		zooService.checkAnimal(new Monkey("Обезьяна Мандаринка",10,2));
		zooService.checkAnimal(new Monkey("Обезьяна Банан",4,8));
		zooService.checkAnimal(new Monkey("Обезьяна Пого",1,6));
		zooService.checkAnimal(new Monkey("Обезьяна Нана",7,10));
		zooService.checkAnimal(new Monkey("Обезьяна Угу",3,3));
		zooService.checkAnimal(new Wolf("Волк Альфа",7));
		zooService.checkAnimal(new Tiger("Тигр Нау",3));

		Computer computer1=new Computer();
		Computer computer2=new Computer();
		Table table1=new Table();
		zooService.takeItem(computer1);
		zooService.takeItem(computer2);
		zooService.takeItem(table1);

		zooService.showAnimalReport();
		zooService.showFriendlyAnimals();
		zooService.showInventory();
	}

}
