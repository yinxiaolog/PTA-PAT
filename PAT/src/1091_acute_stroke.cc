#include <iostream>
#include <queue>

using namespace std;

struct Node {
    int x;
    int y;
    int z;

    Node(int _x, int _y, int _z) {
        x = _x;
        y = _y;
        z = _z;
    }
};

int image[1286][128][60];
bool marked[1286][128][60];

int M, N, L, T;

int X[6] = {1, -1, 0, 0, 0, 0};
int Y[6] = {0, 0, 1, -1, 0, 0};
int Z[6] = {0, 0, 0, 0, 1, -1};

bool IsVisit(int x, int y, int z) {
    if (x < 0 || x >= M || y < 0 || y >= N || z < 0 || z >= L) {
        return false;
    }

    if (image[x][y][z] == 0 || marked[x][y][z]) {
        return false;
    }

    return true;
}

int Bfs(int x, int y, int z) {
    int ans = 0;
    queue<Node> q;
    q.push(Node(x, y, z));
    marked[x][y][z] = true;

    while (!q.empty()) {
        Node node = q.front();
        q.pop();
        ans++;
        for (int i = 0; i < 6; i++) {
            int xx = node.x + X[i];
            int yy = node.y + Y[i];
            int zz = node.z + Z[i];
            if (IsVisit(xx, yy, zz)) {
                q.push(Node(xx, yy, zz));
                marked[xx][yy][zz] = true;
            }
        }
    }

    return ans >= T ? ans : 0;
}

int main() {
    cin >> M >> N >> L >> T;
    for (int i = 0; i < L; i++) {
        for (int j = 0; j < M; j++) {
            for (int k = 0; k < N; k++) {
                int pixel;
                cin >> pixel;
                image[j][k][i] = pixel;
            }
        }
    }

    int ans = 0;
    for (int i = 0; i < L; i++) {
        for (int j = 0; j < M; j++) {
            for (int k = 0; k < N; k++) {
                if (image[j][k][i] == 1 && !marked[j][k][i]) {
                    ans += Bfs(j, k, i);
                }
            }
        }
    }

    cout << ans;
    return 0;
}