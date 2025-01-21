# Пояснения к работе
У меня не получилось запустить игру и проверить все написанные функции "в бою". Я бы мог посмотреть по логам что да как и разобраться. Однако они ничего существенного не дают.

Тестовой среды для алгоритмов также не было предоставлено. Поэтому хоть я и полагаю, что алгоритмы верны, их не получается ни проверить в игре, ни через тесты.

# Сложности алгоритмов

1. **`generate`**:  
   **O(n log n)** — Генерация и сортировка \(n\) юнитов.  
   - **Применяется**: Сортировка юнитов (обычно QuickSort или Timsort) и последующая итерация для добавления.  
   - **Почему такая сложность**: Сортировка массива занимает \(O(n \log n)\), добавление — \(O(n)\), но доминирует \(O(n \log n)\).

2. **`simulate`**:  
   **O(k × n log n)**, где \(k\) — количество раундов, \(n\) — общее число юнитов.  
   - **Применяется**: Сортировка юнитов (\(O(n \log n)\)) и итерация (\(O(n)\)) в каждом раунде.  
   - **Почему такая сложность**: Основной вклад вносит сортировка, повторяющаяся каждый раунд.

3. **`getSuitableUnits`**:  
   **O(n)**, где \(n\) — общее количество юнитов.  
   - **Применяется**: Линейный обход списка с проверкой условий.  
   - **Почему такая сложность**: Каждая клетка проверяется ровно один раз без вложенных циклов.

4. **`getTargetPath`**:  
   **O((WIDTH × HEIGHT) log(WIDTH × HEIGHT))** для A* на графе игрового поля (27 × 21).  
   - **Применяется**: Очередь с приоритетом и проверка соседей.  
   - **Почему такая сложность**: Алгоритм обрабатывает каждую клетку как вершину графа, с O(log V) на операции с очередью.
