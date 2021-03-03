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

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.typography

@Preview
@Composable
fun PuppyCardPreview() {
    dummyPuppy.GetCard()
}

val dummyPuppy = Puppy(
    R.drawable.puppy2, "Buzz", 2, "Labrador", "Big", "Male", 10f, true,
    "Calm and playful dog, he loves his bed and playing ball. He's already vaccinated and sterilized."
)

class Puppy(
    var drawableId: Int,
    var name: String,
    var months: Int,
    var breed: String,
    var size: String,
    var gender: String,
    var weight: Float,
    var vaccinated: Boolean,
    var description: String,
) : Parcelable {
    private var painter: Painter? = null
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readInt() != 0,
        parcel.readString()!!
    )

    @Composable
    public fun getPainter() {
        painter = painterResource(id = drawableId)
    }

    @Composable
    fun GetCard(clickList: ((Puppy) -> Unit)? = null) {
        if (painter == null) {
            painter = painterResource(id = drawableId)
        }
        MaterialTheme {
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clickable {
                        if (clickList != null) {
                            clickList(this)
                        }
                    },
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter!!,
                        contentDescription = "Adopt card for $name",
                        modifier = Modifier
                            .requiredSize(120.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.requiredWidth(12.dp))
                    Column(
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Text(text = name, style = typography.h6)
                        Text(text = "$breed - $months mo", style = typography.h6)
                        Spacer(modifier = Modifier.requiredHeight(4.dp))
                        Text(text = description, style = typography.body1)
                    }
                }
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(drawableId)
        parcel.writeString(name)
        parcel.writeInt(months)
        parcel.writeString(breed)
        parcel.writeString(size)
        parcel.writeString(gender)
        parcel.writeFloat(weight)
        parcel.writeInt(if (vaccinated) 1 else 0)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Puppy> {
        override fun createFromParcel(parcel: Parcel): Puppy {
            return Puppy(parcel)
        }

        override fun newArray(size: Int): Array<Puppy?> {
            return arrayOfNulls(size)
        }
    }
}
