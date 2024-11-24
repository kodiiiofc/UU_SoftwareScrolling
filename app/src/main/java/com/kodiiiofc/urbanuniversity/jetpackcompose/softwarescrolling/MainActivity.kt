package com.kodiiiofc.urbanuniversity.jetpackcompose.softwarescrolling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodiiiofc.urbanuniversity.jetpackcompose.softwarescrolling.ui.theme.SoftwareScrollingTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoftwareScrollingTheme {

                PreviewFun()


            }
        }
    }
}

data class Person(
    val firstName: String,
    val lastName: String,
    val occupation: String
)

private val people = listOf(
    Person("Алексей", "Иванов", "Разработчик"),
    Person("Мария", "Петрова", "Дизайнер"),
    Person("Игорь", "Сидоров", "Тестировщик"),
    Person("Екатерина", "Смирнова", "Менеджер"),
    Person("Дмитрий", "Кузнецов", "Аналитик"),
    Person("Ольга", "Попова", "HR"),
    Person("Николай", "Васильев", "Разработчик"),
    Person("Анна", "Михайлова", "Дизайнер"),
    Person("Сергей", "Новиков", "Тестировщик"),
    Person("Елена", "Федорова", "Менеджер"),
    Person("Виктор", "Морозов", "Аналитик"),
    Person("Татьяна", "Алексеева", "HR"),
    Person("Павел", "Лебедев", "Разработчик"),
    Person("Лариса", "Соловьева", "Дизайнер"),
    Person("Григорий", "Зайцев", "Тестировщик"),
    Person("Наталья", "Борисова", "Менеджер"),
    Person("Владимир", "Карпов", "Аналитик"),
    Person("Ирина", "Макарова", "HR"),
    Person("Андрей", "Семенов", "Разработчик"),
    Person("Алина", "Григорьева", "Дизайнер"),
    Person("Руслан", "Орлов", "Тестировщик"),
    Person("София", "Крылова", "Менеджер"),
    Person("Евгений", "Титов", "Аналитик"),
    Person("Вероника", "Киселева", "HR"),
    Person("Кирилл", "Никитин", "Разработчик"),
    Person("Александра", "Зубова", "Дизайнер"),
    Person("Василий", "Ермаков", "Тестировщик"),
    Person("Юлия", "Тимофеева", "Менеджер"),
    Person("Максим", "Белов", "Аналитик"),
    Person("Евдокия", "Романова", "HR")
)

@Preview(showSystemUi = true)
@Composable
@OptIn(ExperimentalFoundationApi::class)
fun PreviewFun() {


    val groups = people.sortedBy { it.lastName }.groupBy { it.occupation }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
    ) {

        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "В конец",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.DarkGray)
                        .padding(14.dp)
                        .clickable {
                            coroutineScope.launch {
                                listState.animateScrollToItem(people.size + groups.size - 1)
                            }
                        }
                )
            }
        }

        groups.forEach { (occupation, people) ->
            stickyHeader {
                Text(
                    text = occupation,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Magenta)
                        .fillParentMaxWidth()
                )
            }
            items(people) {
                Text(
                    text = "${it.firstName} ${it.lastName}",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)

                )
            }
        }

        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "В начало",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.DarkGray)
                        .padding(14.dp)
                        .clickable {
                            coroutineScope.launch {
                                listState.animateScrollToItem(0)
                            }
                        }
                )
            }
        }

    }
}


