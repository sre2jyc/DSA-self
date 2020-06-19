// # include <bits/stdc++.h>
// using namespace std ;
// typedef long long int ll ;

// void addEdge(vector<vector<ll> > &graph , ll u, ll v){
//     graph[u].push_back(v) ;
//     graph[v].push_back(u) ;
// }



// void dfs(vector<vector<ll> > &graph ,ll starting){
//     vector<ll> check(graph.size()+1,0) ;
//     stack <ll> S ;
//     S.push(starting) ;
//     while(S.size()!=0){
//         ll x = S.top() ;
//         //check[x] = 1 ;
//         S.pop() ;
//         if(check[x]==0){
//             cout << x <<" " ;
//             check[x] = 1 ;
//         }
//         for(ll i=0;i<graph[x].size();i++){
//             // sort(graph[i].begin(),graph[i].end()) ;
//             if(check[graph[x][i]]==0){
//             S.push(graph[x][i]) ;
//             //check[graph[x][i]] = 1 ;
//             }
//         }
        
            
            
//     }
// } 


// int main(){
//     ll n;
//     cin >> n ;
//     vector <vector<ll> > graph ;
//     graph.resize(n+1) ;
//     addEdge(graph,0, 1); 
//     addEdge(graph,1, 2); 
//     addEdge(graph,0, 3); 
//     addEdge(graph,3, 4); 
//     addEdge(graph,4, 5); 
//     addEdge(graph,3, 6); 
//     addEdge(graph,1, 7);
//     addEdge(graph,7, 8);
//     addEdge(graph,8, 5);

//     dfs(graph,0) ;

// }

//DFS USING RECURSION

# include <bits/stdc++.h>
using namespace std ;
typedef long long int ll ;

// void dfs_recursion(stack<ll> &S , vector<vector<ll> > &G,vector<ll> &check){
//         ll t = S.top() ;
//         S.pop() ;
//         cout << t <<" " ;
//         for(ll i=0;i<G[t].size();i++){
//             if(check[G[t][i]]==-1){
//                 S.push(G[t][i]) ;
//                 check[G[t][i]] = 0 ;
//                 dfs_recursion(S,G,check) ;
//             }
//         }
//     }

// void dfs(vector<vector<ll> > &G , ll n, ll s){
//     vector<ll> check(n+1,-1) ;
//     stack <ll> S ;
//     S.push(s) ;
//     check[s]= 0 ;
    
//     dfs_recursion(S,G,check) ;
// }

// no stack is required
void dfs_recursion(ll head , vector<vector<ll> > &G,vector<ll> &check){
        ll t = head ;
        
        cout << t <<" " ;
        for(ll i=0;i<G[t].size();i++){
            if(check[G[t][i]]==-1){
                head = G[t][i] ;
                check[G[t][i]] = 0 ;
                dfs_recursion(head,G,check) ;
            }
        }
    }

void dfs(vector<vector<ll> > &G , ll n, ll head){
    vector<ll> check(n+1,-1) ;
    
    check[head]= 0 ;
    
    dfs_recursion(head,G,check) ;


    

}

int main(){
    ll n,m ,x,y;
    cin >> n >> m ;
    vector <vector<ll> >G ;
    G.resize(n+1) ;
    for(ll i=0;i<m;i++){
      cin >> x >> y ;
      G[x].push_back(y) ;
      G[y].push_back(x) ;
    }
    ll head ;
    cin >> head ;
    
    dfs(G,n,head) ;
   
}
