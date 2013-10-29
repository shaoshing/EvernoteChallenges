package main

import "fmt"

func main() {
	// Load numbers
	numberLength := 0
	fmt.Scanf("%d", &numberLength)
	numbers := make([]int, numberLength)
	for i := 0; i < numberLength; i++ {
		fmt.Scanf("%d", &numbers[i])
	}

	// Calculate the total product
	var totalProduct int64 = 1
	countOfZero := 0
	for _, number := range numbers {
		if number != 0 {
			totalProduct *= int64(number)
		} else {
			if countOfZero++; countOfZero == 2 {
				break
			}
		}
	}

	// Print out results
	for _, number := range numbers {
		var output int64
		switch countOfZero {
		case 0:
			output = totalProduct / (int64(number))
		case 1:
			if number == 0 {
				output = totalProduct
			} else {
				output = 0
			}
		case 2:
			output = 0
		}

		fmt.Printf("%d\n", output)
	}
}
