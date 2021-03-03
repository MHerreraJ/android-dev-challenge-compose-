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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

class PuppyDetailsActivity : AppCompatActivity() {
    companion object {
        val PUPPY_DETAILS_EXTRA_DATA = "PUPPY_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val puppy: Puppy = intent.getParcelableExtra<Puppy>(PUPPY_DETAILS_EXTRA_DATA)!!
        setContent {
            MyTheme() {
                PuppyDetailsComposable(puppy)
            }
        }
    }
}

@Composable
fun PuppyDetailsComposableAttribute(attrName: String, value: String) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "$attrName: ",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            style = typography.h6
        )
        Text(
            text = value,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light,
            fontSize = 19.sp
        )
    }
}

@Composable
fun PuppyDetailsComposable(dog: Puppy) {
    Surface(color = MaterialTheme.colors.background) {
        MaterialTheme {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.requiredHeight(12.dp))
                    Image(
                        painterResource(id = dog.drawableId),
                        contentDescription = "Adopt card for ${dog.name}",
                        modifier = Modifier
                            .requiredSize(170.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = dog.name,
                        style = typography.h3,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(modifier = Modifier.requiredHeight(5.dp))
                    Text(
                        text = dog.description,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        fontSize = 19.sp
                    )
                }
                Spacer(modifier = Modifier.requiredHeight(10.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    PuppyDetailsComposableAttribute("Breed", dog.breed)
                    PuppyDetailsComposableAttribute("Age", "${dog.months} months")
                    PuppyDetailsComposableAttribute("Size", dog.size)
                    PuppyDetailsComposableAttribute("Gender", dog.gender)
                    PuppyDetailsComposableAttribute("Weight", "${dog.weight} kg")
                    PuppyDetailsComposableAttribute("Vaccinated", if (dog.vaccinated) "Yes" else "No")
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DetailsActivityLightPreview() {
    MyTheme {
        PuppyDetailsComposable(dummyPuppy)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DetailsActivityDarkPreview() {
    MyTheme(darkTheme = true) {
        PuppyDetailsComposable(dummyPuppy)
    }
}
