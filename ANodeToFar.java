import java.util.*;

class ANodeToFar{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();

		int numNodes = in.nextInt();
		int cases = 1;

		while (numNodes != 0){
			ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> vals = new ArrayList<Integer>();
			for (int i = 0; i < 1000; i++){
				list.add(new ArrayList<Integer>());
			}

			for (int i = 0; i < numNodes; i++){
				int a = in.nextInt();
				int b = in.nextInt();

				list.get(a).add(b);
				list.get(b).add(a);

				if (!vals.contains(a)){
					vals.add(a);
				}
				if (!vals.contains(b)){
					vals.add(b);
				}
			}

			

			
			int startNode = in.nextInt();
			int ttl = in.nextInt();

			while(startNode != 0 && ttl != 0){
				int origTTL = ttl;
				boolean [] visited = new boolean[1000];
				visited[startNode] = true;

				for (int i = 0; i < 1000; i++){
					if (!vals.contains(i)){
						visited[i] = true;
					} 
				}
				

				Queue <Integer> q = new LinkedList<Integer>();
				q.add(startNode);

				int current = 0;
				while(!q.isEmpty()){
					current = q.remove();

					ArrayList<Integer> neighbors = list.get(current);

					for (int k = 0; k < neighbors.size(); k++){
						if (!visited[neighbors.get(k)] && ttl > 0){
							visited[neighbors.get(k)] = true;
							ttl--;
							q.add(neighbors.get(k));
						}
					}
				}
				// check how many are not visited
				int nvCount = 0;
				for (int i = 0; i < numNodes + 1; i++){
					if (!visited[i]){
						nvCount++;
					}
				}

				string.append("Case #"+cases+": "+nvCount+" nodes not reachable from node"+startNode+" with TTL = "+origTTL+".\n");
				cases++;

				startNode = in.nextInt();
				ttl = in.nextInt();
			}


			numNodes = in.nextInt();
		}
		System.out.print(string.toString());
	}

	public static class Point{
		public static ArrayList<Integer> neighborsList; 
	}
}
