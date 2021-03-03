/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

val pupyDataSet = arrayListOf(
    Puppy(
        R.drawable.puppy1, "Vaca", 3, "Dachshund", "Small", "Female", 3f, true,
        "I'm a very playful dog, waiting for an owner who knows how to satisfy my excess energy."
    ),
    Puppy(
        R.drawable.puppy2, "Buzz", 2, "Labrador", "Big", "Male", 5f, true,
        "Calm and playful dog, he loves his bed and playing ball. He's already vaccinated and sterilized."
    ),
    Puppy(
        R.drawable.puppy3, "Darwin", 4, "Boxer", "Big", "Male", 8.2f, false,
        "Cute puppy of medium size, very well behaved, ideal for home or apartment."
    ),
    Puppy(
        R.drawable.puppy4, "Lola", 2, "Poodle", "Medium", "Female", 4.5f, true,
        "Lola is a very active and energetic puppy. She's already vaccinated, dewormed and sterilized."
    ),
    Puppy(
        R.drawable.puppy5, "Murphy", 5, "Rottweiler", "Big", "Male", 9.7f, false,
        "Protective dog, requires constant vigilance and care."
    ),
    Puppy(
        R.drawable.puppy6, "Danka", 3, "Dalmatian", "Big", "Female", 6.8f, true,
        "Hi!, I'm looking for a home, I am super docile and sociable."
    ),
    Puppy(
        R.drawable.puppy7, "Ginger", 3, "Husky", "Big", "Female", 7.2f, true,
        "She is a little scared puppy,  but very pretty, she is delivered with a complete health protocol."
    ),
    Puppy(
        R.drawable.puppy8, "Cooper", 4, "Corgi", "Medium", "Male", 5.1f, true,
        "Cooper is already dewormed and vaccinated. Small size, playful and very loving puppy."
    ),
    Puppy(
        R.drawable.puppy9, "Marvin", 2, "Bull Terrier", "Medium", "Male", 4.7f, false,
        "Marvin is looking for a home where he is given a lot of respect and affection, only responsible people."
    ),
    Puppy(
        R.drawable.puppy10, "Loki", 6, "Xoloitzcuintle", "Big", "Male", 11.3f, true,
        "I am a super playful and active puppy, I'm spayed, vaccinated and dewormed"
    )

)

typealias OnPuppySelected = (Puppy) -> Unit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(
                    pupyDataSet,
                    puppySelectedEvt = {
                        val intent = Intent(this, PuppyDetailsActivity::class.java)
                        intent.putExtra(PuppyDetailsActivity.PUPPY_DETAILS_EXTRA_DATA, it)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(puppies: List<Puppy>, puppySelectedEvt: OnPuppySelected? = null) {
    for (puppy in puppies) puppy.getPainter()
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.requiredHeight(10.dp))
            Row {
                Spacer(Modifier.requiredWidth(20.dp))
                Text(
                    text = "Adopt a puppy!",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light,
                    fontSize = 30.sp
                )
            }

            LazyColumn {
                items(items = puppies) { item ->
                    item.GetCard(puppySelectedEvt)
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(pupyDataSet)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(pupyDataSet)
    }
}
