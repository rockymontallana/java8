package exercisethree;

import java.util.*;
import java.util.stream.Collectors.*;
import java.util.stream.*;

public class HorseRace {
	private final List<String> WARCRIES = 
		Arrays.asList(null, "warCry1", "warCry2", "warCry3", "warCry4", "warCry5");
		
	private Random random = new Random();
	private Scanner userInput = new Scanner(System.in);
	
	public HorseRace() throws Exception{
		int distance = 0;
		int numberOfHorses = 0;
		boolean isLessThanTwo = true;
		
		System.out.print("Enter the Distance in yards(220-770 only): ");
		distance = checkIntegerInput(770);
		
		System.out.print("Enter the number of horses (2-15 only): ");
		numberOfHorses = checkIntegerInput(15);
		
		List<Horses> healthyHorses = setHorsesProperties(distance, numberOfHorses);
		
		while(isLessThanTwo == true) {
			if(healthyHorses.size() < 2) {
				System.out.println("Number of Healthy Horses is lessthan 2");
				System.out.print("Please enter number of Horses: ");
				numberOfHorses = checkIntegerInput(15);
				healthyHorses = setHorsesProperties(distance, numberOfHorses);
			}else {
				isLessThanTwo = false;
			}
		}
		
		raceStart(distance, healthyHorses);
	}
		
	public List<Horses> setHorsesProperties(int distance, int numberOfHorses) {
		List<Horses> horseList = new ArrayList<>();
		
		final String NAME = "horse";
		Boolean winner = false;
		
		for(int i = 1; i <= numberOfHorses; i++) {
			horseList.add(new Horses(NAME + i, 
									random.nextBoolean(), 
									0, 
									WARCRIES.get(random.nextInt(WARCRIES.size()))));
		}
		
		List<Horses> healthyHorses = horseList.stream()
											  .filter(e -> e.getHealthy() == true)
											  .map(e -> new Horses(e.getName().toUpperCase(),
																   e.getHealthy(),
																   e.getDistanceTraveled(),
																   e.getWarCry()))
											  .collect(Collectors.toList());
											  
		return healthyHorses;
	}
	
	
	public void raceStart(int distance, List<Horses> healthyHorses) throws Exception{
		boolean finished = false;
		Thread.sleep(500);
		
		System.out.println("Race starts in:");
		
		for(int i = 3; i > 0; i--) {
			System.out.println(i);
			Thread.sleep(1000);
		}
		
		while(finished) {
				healthyHorses = healthyHorses.parallelStream()
										 .map(e -> new Horses(e.getName(),
															  e.getHealthy(),
															  e.getDistanceTraveled() + random.nextInt(10) + 1,
															  e.getWarCry()))
										 .collect(Collectors.toList());
										 
				System.out.println("Horse Name \tDistance Travelled \tDistance Left");
											 
				healthyHorses.forEach(e -> System.out.println(e.getName() + " \t\t\t" +
															  e.getDistanceTraveled() + " \t\t\t" + 
															  (distance - e.getDistanceTraveled())));
														  
				System.out.print("\n\n");
				Thread.sleep(1000);
				finished = checkWinner(healthyHorses, distance);
		}
	
		System.out.println("---------------Race Summary---------------");
		System.out.println("Horse Name \tTotal Distance");
		
		healthyHorses.forEach(e -> System.out.println(e.getName() + " \t\t\t" + e.getDistanceTraveled()));
	}
	
	public boolean checkWinner(List<Horses> healthyHorses, int distance) {
		List<Horses> winnerHorse = healthyHorses.stream()
												.filter(e -> e.getDistanceTraveled() >= distance)
												.map(e -> new Horses(e.getName(),
															  e.getHealthy(),
															  e.getDistanceTraveled() + random.nextInt(10) + 1,
															  e.getWarCry()))
												.collect(Collectors.toList());
		if(winnerHorse.isEmpty()){
			return false;
		}else {
			Horses winner = winnerHorse.stream()
									 .max(Comparator.comparingInt(Horses::getDistanceTraveled))
									 .get();
			displayWinner(winner);
		}
		return true;
	}
	
	public void displayWinner(Horses winner) {
		System.out.print("\n\n");
		System.out.println("---------------Winner---------------");
		System.out.println(winner.getName() + "\t" + checkWarCry(winner.getWarCry()));
		System.out.print("\n\n");
	}
	
	public String checkWarCry(String winnersWarCry) {
		String warCry = Optional.ofNullable(winnersWarCry).orElse("No War Cry");
		return warCry;
	}
	
	int checkIntegerInput(int limit) {
		int numInput = 0;
		boolean isNotValid = true;
		
		while (isNotValid) {
			try{
				numInput = userInput.nextInt();
				
				if(limit == 15) {
					if(numInput <= limit && numInput >= 2)
						break;
					else
						System.out.print("Input out of range! Please enter 2-" + limit + " only: ");
				}else {
					if((numInput <= 770 && numInput >= 220))
						break;
					else
						System.out.print("Input out of range! Please enter 220-" + limit + " only: ");
				}
            }
			catch(InputMismatchException e){
				userInput.next();
				System.out.print("invalid input! Please try another: ");
			}
		}
		
		return numInput;
	}
}