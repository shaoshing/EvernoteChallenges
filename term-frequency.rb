# Load inputs
word_length = gets.to_i
words = []
word_length.times.each{ words << gets.strip }
output_word_length = gets.to_i

## lexicographical

# Count word frequencies
# Example value of word_frequencies:
#   {"Fee": 12, "Fum": 2}
word_frequencies = {}
words.each do |word|
  word_frequencies[word] ||= 0
  word_frequencies[word] += 1
end


WORD = 0
FREQUENCY = 1
sorted_word_frequencies = word_frequencies.to_a.sort do |a, b|
  f = b[FREQUENCY] <=> a[FREQUENCY]
  f != 0 ? f : a[WORD] <=> b[WORD]
end

sorted_word_frequencies.first(output_word_length).each{|wf| puts wf[WORD]}
