package mx.multivelopers.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.multivelopers.api.model.Item;

/**
 * Simple Rest API 
 * 
 * @author dsancho
 *
 */
@RestController
public class ApiRestController {
	
	@RequestMapping(method=RequestMethod.GET, path="/items")
	Collection<Item> getOrderedRandomList() {
		Collection<Item> collection = new ArrayList<>();
		int number = 0;
		for (int i=0; i<5; i++) {
			number = number + new Random().nextInt(100);
			collection.add(new Item(Long.valueOf(""+i), "name_"+ i, "description_"+i));
		}
		return collection;
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/items")
	Integer create(@RequestBody Item item) {
		Integer result = 0;
		if(null != item) {
			result = 1;
		}
		return result;
	}
	

}
