package com.example.p51

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TugasScreen(modifier: Modifier = Modifier) {
    val headerHeight = dimensionResource(R.dimen.header_height)
    val overlap = dimensionResource(R.dimen.card_top_overlap)

    Scaffold(
        containerColor = colorResource(R.color.background)
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(R.drawable.foto1),
                contentDescription = stringResource(
                    R.string.header_description
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
                    .align(Alignment.TopCenter)
            )

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = headerHeight - overlap),
                shape = RoundedCornerShape(
                    topStart = dimensionResource(R.dimen.card_radius),
                    topEnd = dimensionResource(R.dimen.card_radius)
                ),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.white)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = dimensionResource(
                        R.dimen.card_elevation
                    )
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(
                            dimensionResource(R.dimen.card_padding)
                        )
                ) {
                    Text(
                        text = stringResource(R.string.form_title),
                        color = colorResource(R.color.blue),
                        fontSize = dimensionResource(
                            R.dimen.title_size
                        ).value.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(
                            Alignment.CenterHorizontally
                        )
                    )

                    Spacer(
                        modifier = Modifier.height(
                            dimensionResource(R.dimen.small_spacing)
                        )
                    )

                    Text(
                        text = stringResource(R.string.form_subtitle),
                        color = colorResource(R.color.text_secondary),
                        fontSize = dimensionResource(
                            R.dimen.body_size
                        ).value.sp,
                        modifier = Modifier.align(
                            Alignment.CenterHorizontally
                        )
                    )

                    Spacer(
                        modifier = Modifier.height(
                            dimensionResource(R.dimen.section_spacing)
                        )
                    )

                    BiodataForm()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiodataForm() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var selectedExperience by remember { mutableStateOf("") }
    var experienceExpanded by remember { mutableStateOf(false) }

    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }

    var androidSkill by remember { mutableStateOf(false) }
    var uiuxSkill by remember { mutableStateOf(false) }
    var webSkill by remember { mutableStateOf(false) }

    val experienceOptions = stringArrayResource(
        R.array.experience_options
    )

    val fieldSpacing = dimensionResource(R.dimen.field_spacing)

    Column(
        verticalArrangement = Arrangement.spacedBy(fieldSpacing)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(fieldSpacing)
        ) {
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = {
                    Text(stringResource(R.string.first_name))
                },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = {
                    Text(stringResource(R.string.last_name))
                },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
        }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(stringResource(R.string.email))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(fieldSpacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                OutlinedTextField(
                    value = selectedDate?.let { millis ->
                        SimpleDateFormat(
                            stringResource(R.string.date_pattern),
                            Locale.getDefault()
                        ).format(Date(millis))
                    }.orEmpty(),
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text(stringResource(R.string.birth_date))
                    },
                    trailingIcon = {
                        Text(
                            text = stringResource(R.string.calendar_symbol),
                            modifier = Modifier.clickable {
                                showDatePicker = true
                            }
                        )
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showDatePicker = true
                        }
                )
            }

            Box(modifier = Modifier.weight(1f)) {
                OutlinedTextField(
                    value = selectedExperience,
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text(stringResource(R.string.experience))
                    },
                    trailingIcon = {
                        Text(
                            text = stringResource(R.string.dropdown_symbol),
                            modifier = Modifier.clickable {
                                experienceExpanded = true
                            }
                        )
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            experienceExpanded = true
                        }
                )

                DropdownMenu(
                    expanded = experienceExpanded,
                    onDismissRequest = {
                        experienceExpanded = false
                    }
                ) {
                    experienceOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedExperience = option
                                experienceExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier.height(
                dimensionResource(R.dimen.small_spacing)
            )
        )

        Text(
            text = stringResource(R.string.choose_skill),
            color = colorResource(R.color.text_primary),
            fontSize = dimensionResource(R.dimen.body_size).value.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SkillCheckbox(
                label = stringResource(R.string.skill_android),
                checked = androidSkill,
                onCheckedChange = { androidSkill = it }
            )

            SkillCheckbox(
                label = stringResource(R.string.skill_uiux),
                checked = uiuxSkill,
                onCheckedChange = { uiuxSkill = it }
            )

            SkillCheckbox(
                label = stringResource(R.string.skill_web),
                checked = webSkill,
                onCheckedChange = { webSkill = it }
            )
        }

        Spacer(
            modifier = Modifier.height(
                dimensionResource(R.dimen.section_spacing)
            )
        )

        val formValid = firstName.isNotBlank() &&
                lastName.isNotBlank() &&
                email.isNotBlank() &&
                android.util.Patterns.EMAIL_ADDRESS
                    .matcher(email).matches() &&
                selectedDate != null &&
                selectedExperience.isNotBlank() &&
                (androidSkill || uiuxSkill || webSkill)


    }
}