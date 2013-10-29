package main

import (
	"fmt"
	"sort"
)

type Word struct {
	Name  string
	Count int32
}

// Implement the sorting
type Words []*Word

func (words Words) Len() int {
	return len(words)
}
func (words Words) Less(i, j int) bool {
	if words[i].Count != words[j].Count {
		return words[i].Count > words[j].Count
	}
	return words[i].Name < words[j].Name
}
func (words Words) Swap(i, j int) {
	words[i], words[j] = words[j], words[i]
}

func main() {
	// Load words
	wordLength := 0
	fmt.Scanf("%d", &wordLength)
	rawWords := make([]string, wordLength)
	for i := 0; i < wordLength; i++ {
		fmt.Scanf("%s", &rawWords[i])
	}

	// Count the frequencies
	frequencies := map[string]int32{}
	for _, word := range rawWords {
		if _, ok := frequencies[word]; ok {
			frequencies[word] += 1
		} else {
			frequencies[word] = 1
		}
	}

	// Sort words
	words := Words{}
	for wordName, count := range frequencies {
		words = append(words, &Word{wordName, count})
	}
	sort.Sort(words)

	// Print words
	outputWordLength := 0
	fmt.Scanf("%d", &outputWordLength)
	for i := 0; i < outputWordLength; i++ {
		fmt.Println(words[i].Name)
	}
}
