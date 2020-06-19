#include <bits/stdc++.h>
using namespace std;
#define MOD 1000000007
typedef long long ll;
typedef pair<ll,ll> Pll ;
 
void fastscan(ll &number) 
{ 
    //variable to indicate sign of input number 
    bool negative = false; 
    register int c; 
  
    number = 0; 
  
    // extract current character from buffer 
    c = getchar(); 
    if (c=='-') 
    { 
        // number is negative 
        negative = true; 
  
        // extract the next character from the buffer 
        c = getchar(); 
    } 
  
    // Keep on extracting characters if they are integers 
    // i.e ASCII Value lies from '0'(48) to '9' (57) 
    for (; (c>47 && c<58); c=getchar()) 
        number = number *10 + c - 48; 
  
    // if scanned input has a negative sign, negate the 
    // value of the input number 
    if (negative) 
        number *= -1; 
}
 
ll mod_ex(ll x,ll y,ll p){
    ll res = 1 ;
    if(x%p == 0) return 0 ;
    else{
        x = (x%p) ;
        while(y>0){
            if(y&&1 == 1){
                res = (res*x)%p ;
                y -- ;
            }
            else{
                y = y/2 ;
                x = (x*x)%p ;
 
            }
        }
    }
    return res ;
}
 
ll aks_prime(ll p){
 
    ll S = 1 - (pow(2,p)-1) ;
    if(S%p==0) return 1 ;
    else return 0 ;
 
 
}
 
void SieveOfEratosthenes(int n,vector<ll> &get_primes){
    // return an array consisting of all primes from 1 to n 
  
    bool prime[n+1]; 
   memset(prime, true, sizeof(prime)); 
  
    for (int p=2; p*p<=n; p++) 
    { 
        
        if (prime[p] == true) 
        { 
         
            for (int i=p*p; i<=n; i += p) {
                prime[i] = false; }
        } 
    } 
  
    for (int p=2; p<=n; p++){ 
       if (prime[p]){ 
           get_primes.push_back(p) ;
          // cout << p << " "; 
          }
          }
 
    //      vector<ll> :: iterator it ;
    //       it = lower_bound(get_primes.begin(),get_primes.end(),l) ;
    //   // it -= get_primes.begin() ;
    //   ll i =  it-get_primes.begin() ;
    //    for(i;i<get_primes.size();i++){
    //        cout << get_primes[i] <<"\n" ;
    //    }
} 
ll binexp(ll a, ll b, ll m) {
    a %= m;
    ll res = 1;
    while (b > 0) {
        if (b & 1)
            res = res * a % m;
        a = a * a % m;
        b >>= 1;
    }
    return res;
}
bool probablyPrimeFermat(int n, int iter) {
    if (n < 4)
        return n == 2 || n == 3;
 
    for (int i = 0; i < iter; i++) {
        int a = 2 + rand() % (n - 3);
        if (binexp(a, n - 1, n) != 1)
            return false;
    }
    return true;
}

ll binpow(ll a,ll b){
    ll res = 1;
    while (b > 0){
        if (b & 1)
            res = res * a;
        a = a * a;
        b >>= 1;
    }
    return res;
}
  
 
 int fenwick[N] = {0} ;

void update_fen(int x,int diff){
  while(x < N){
    fenwick[x] += diff ;
    x += (x & -x) ;
    // addition of lowest bit
  }
}

int pref_sum_fen(int i){
  int sum = 0 ;
  // finding prefix sum from 0 to i
  while(i > 0){
    sum += fenwick[i] ;
    // subtract lowest bit
    i -= (i & -i) ;
  }

  return sum ;

}

void extract_fen(int x){
     int low = 0 ;
     int high = N ;
     int ans = -1 ;
     while(low <= high){
       int mid = (low+high)/2 ;
       if(pref_sum_fen(mid) >= x ){
         ans = mid ;
         high = mid -1 ;
       }
       else{
         low = mid+1 ;
       }

     }

     // we get the reqd index in ans 
     // update tree
     update_fen(ans,-1) ;
}

int finding_ans(){
  int h = N ;
  int l = 0 ;
  int a = -1 ;
  while(h >= l){
     int m = (h+l)/2 ;
     if(pref_sum_fen(m) >= 1){
        a=m ;
        h = m-1 ;

     }
     else{
       l = m+1 ;
     }
  }
  return a ;
}

void ex_binlift(int x){
  int current = 0 , sum = 0 ;
  for(int i=log2(N);i>=0;i--){
    if((current+(1<<i) < N ) && sum+fenwick[current + (1<<i)] < x ){
      current += (1 << i) ;
      sum += fenwick[current] ;
    }
  }
  update_fen(current+1,-1) ;
}
 
// int fa_bl(int x){
//   int current = 0 , sum = 0 ;
//   for(int i=log2(N);i>=0;i--){
//     if((current+(1<<i) < N ) && sum+fenwick[current + (1<<i)] < x ){
//       current += (1 << i) ;
//       sum += fenwick[current] ;
//     }
//   }

//   if(sum == 0){
//     return sum ;
//   }
//   else {
//     return (current+1) ;
//   }

// }
 
 
int main() {
	
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
}