package com.ahgitdevelopment.course.customexamples.features.screens.datastore

//
//@Composable
//fun DataStoreResult(
//    viewModel: DataStoreViewModel = hiltViewModel()
//) {
//    val data = viewModel.phonebook.value
//    Column(
//        modifier = Modifier.padding(16.dp),
//        verticalArrangement = Arrangement.Center
//    ) {
//        if (data != null) {
//            Text(
//                text = "Hello, ${data.name}!",
//                modifier = Modifier.padding(bottom = 8.dp),
//                style = MaterialTheme.typography.h6
//            )
//            Text(
//                text = "Your phone is ${data.phone}!",
//                modifier = Modifier.padding(bottom = 8.dp),
//                style = MaterialTheme.typography.h6
//            )
//            Text(
//                text = "Your address is ${data.address}!",
//                modifier = Modifier.padding(bottom = 8.dp),
//                style = MaterialTheme.typography.h6
//            )
//        } else {
//            Text(
//                text = "No hay datos guardados",
//                modifier = Modifier.padding(bottom = 8.dp),
//                style = MaterialTheme.typography.h6
//            )
//        }
//    }
//}
//
//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun DefaultPreviewSplashScreen() {
//    Splash(2_000)
//}
