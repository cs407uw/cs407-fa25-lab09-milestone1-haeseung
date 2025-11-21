package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        // TODO: Call reset()
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        // a₀ is the previous acceleration (accX, accY)
        // a₁ is the current acceleration (xAcc, yAcc)
        // v₀ is the previous velocity (velocityX, velocityY)
        // t₁ - t₀ = dT

        // Equation (1): v₁ = v₀ + (1/2)(a₁ + a₀)(t₁ - t₀)
        val newVelocityX = velocityX + 0.5f * (xAcc + accX) * dT
        val newVelocityY = velocityY + 0.5f * (yAcc + accY) * dT

        // Equation (2): l = v₀ ⋅ (t₁ - t₀) + (1/6)(t₁ - t₀)² ⋅ (3a₀ + a₁)
        val distanceX = velocityX * dT + (1.0f / 6.0f) * dT * dT * (3.0f * accX + xAcc)
        val distanceY = velocityY * dT + (1.0f / 6.0f) * dT * dT * (3.0f * accY + yAcc)

        // Update position
        posX += distanceX
        posY += distanceY

        // Update velocity
        velocityX = newVelocityX
        velocityY = newVelocityY

        // Update acceleration
        accX = xAcc
        accY = yAcc

        // Check boundaries after updating position
        checkBoundaries()
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)
        // Check left boundary
        if (posX < 0f) {
            posX = 0f
            velocityX = 0f
            accX = 0f
        }
        // Check right boundary
        if (posX > backgroundWidth - ballSize) {
            posX = backgroundWidth - ballSize
            velocityX = 0f
            accX = 0f
        }
        // Check top boundary
        if (posY < 0f) {
            posY = 0f
            velocityY = 0f
            accY = 0f
        }
        // Check bottom boundary
        if (posY > backgroundHeight - ballSize) {
            posY = backgroundHeight - ballSize
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)        
        
        posX = (backgroundWidth - ballSize) / 2f
        posY = (backgroundHeight - ballSize) / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}