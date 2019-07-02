#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int Nc;
    cin >> Nc;
    vector<int> coupons;
    for (int i = 0; i < Nc; i++) {
        int tmp;
        cin >> tmp;
        coupons.push_back(tmp);
    }

    int Np;
    cin >> Np;
    vector<int> products;
    for (int i = 0; i < Np; i++) {
        int tmp;
        cin >> tmp;
        products.push_back(tmp);
    }

    sort(coupons.begin(), coupons.end());
    sort(products.begin(), products.end());

    int ans = 0;
    for (int i = 0, j = 0; coupons[i] < 0 && products[j] < 0 && i < coupons.size() && j < products.size(); i++, j++) {
        ans += coupons[i] * products[j];
    }
    for (int i = coupons.size() - 1, j = products.size() - 1;
         coupons[i] > 0 && products[j] > 0 && i >= 0 && j >= 0; i--, j--) {
        ans += coupons[i] * products[j];
    }

    cout << ans;
    return 0;
}