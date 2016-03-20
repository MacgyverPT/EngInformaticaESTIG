def merge_sort (A):
	merge_sort2 (A, 0, len(A)-1)
	

def merge_sort2 (A, first, last):
	if first < last:
		breakMiddle = (first + last) // 2
		merge_sort2 (A, first, breakMiddle)
		merge_sort2 (A, breakMiddle + 1, last)
		merge (A, first, breakMiddle, last)

		
def merge (A, first, breakMiddle, last):
	L = A[first:breakMiddle]
	R = A[breakMiddle: last + 1]
	
	L.append(999999999) #valor maximo de ordenacao a esquerda
	R.append(999999999) #valor maximo de ordenacao a direita
	
	i = j = 0
	
	for k in range(first, last + 1):
		if L[i] <= R[j]:
			A[k] = L[i]
			i += 1
		else:
			A[k] = R[j]
			j += 1
			
			

#####
A = [5, 2, 6, 4, 1, 3, 7, 10, 8, 11]

print "\nArray: ", A
merge_sort(A)

print "Array ordenado: ", A
for x in A:
    print x