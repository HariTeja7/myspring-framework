package com.myspring.springdemo.shield;

import com.myspring.annotation.Autowired;
import com.myspring.annotation.Component;
import com.myspring.annotation.MainClass;
import com.myspring.annotation.type.Scope;
import com.myspring.springdemo.avengers.Avenger;
import com.myspring.springdemo.avengers.HulkBuster;
import com.myspring.springdemo.avengers.IronMan;
import com.myspring.annotation.Main;

/*
 * 
 * @To test this frame annotate any class with @MainClass and any void no args
 * method with @Main. This will act as Main Class and annotated method will act
 * as main method
 * 
 */
@Component
@MainClass
public class Shield {

	@Autowired
	private Avenger captainAmerica;

	@Autowired
	private Avenger ironMan;

	@Autowired(name = "ironMan")
	private IronMan ironMan2;

	@Autowired(name = "ironMan", scope = Scope.PROTOTYPE)
	private IronMan ironMan3;

	@Autowired
	private HulkBuster hulkBuster;

	@Autowired
	private Avenger spiderMan;

	@Autowired
	private Avenger hulk;

	@Main
	public void getAllAvengers() {
		// demo to show auto wiring
		System.out.println("Captain America : " + captainAmerica.message());

		// Autowired will have default scope as single ton. both ironman and ironman 2
		System.out.println("Iron Man : " + ironMan);
		System.out.println("Iron Man 2: " + ironMan2);
		// objects are same instance because of default scope
		System.out.println(ironMan.equals(ironMan2));
		// iron man 3 will have a new instance as specified scope is prototype
		System.out.println("Iron Man 3: " + ironMan3);

		// HulkBuster extends interface and annotated with component
		System.out.println("Hulk Buster : " + hulkBuster);

		// SpiderMan has ironman as dependency and it is annotated with autowire
		System.out.println("Spider Man : " + spiderMan.message());

		// Hulk is configured in bean configuration
		System.out.println("Hulk : " + hulk.message());
	}

}
