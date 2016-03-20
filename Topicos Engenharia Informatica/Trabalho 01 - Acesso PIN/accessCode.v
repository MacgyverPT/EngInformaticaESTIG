/*
Access by PIN

Date: 1/3/2016
Authors: Pedro Caixinha, Nº4437
	 Miguel Rosa, Nº6219
			
NOTA: Os ficheiros do projecto estão dentro da pasta /accesscode
*/

module accessCode(
	input	CLOCK_50,														// 50 MHz CLOCK
	input	[2:0]	KEY,														// KEY BUTTONS
	input [9:0] SW,														// SWITCHES
	output reg [0:0] LEDR,					// RED LEDS
	output reg [6:0] HEX0, HEX1, HEX2, HEX3, HEX4, HEX5	// 7 SEGMENT DISPLAYS
);

reg [3:0] puk[7:0];		// PUK CODE
reg [3:0] pukTemp[7:0];		// AUXILIARY VARIABLE
reg [3:0] pukIndex;		// PUK INDEX

reg [3:0] pin[3:0];		// PIN CODE
reg [3:0] pinTemp [3:0]; 	// AUXILIARY VARIABLE
reg [2:0] pinIndex;		// PIN INDEX

reg [1:0] attemptsByTime;	// TIMEOUT ATTEMPTS
reg [1:0] attemptsByError;	// ERROR ATTEMPTS
reg [24:0] timer;		// PIN INSERTION TIMER
reg timerStatus = 1'b0;		// TIMER STATUS
reg [5:0] sec;			// SECOND

reg lockOut = 1'b0;		// LOCKOUT STATUS

reg evt;			// KEY PRESS EVENT

always @ (SW[9])
begin
	if(SW[9] == 0)
	begin
		// PIN INSERT MODE
		HEX5 <= ~7'b1110011; // Letter P
		HEX4 <= ~7'b1000000; // Symbol -
	end
	else if(SW[9] == 1 && lockOut == 0)
	begin
		// PIN CHANGE MODE
		HEX5 <= ~7'b1010100;	// Letter n
		HEX4 <= ~7'b1000000; // Symbol -
	end
	else if(SW[9] == 1 && lockOut == 1)
	begin
		// PUK INSERT MODE
		HEX5 <= ~7'b1110011; // Letter P
		HEX4 <= ~7'b0111110; // Letter U
	end
end


always @ (posedge(CLOCK_50))
begin
	evt <= KEY[0] & KEY[1] & KEY[2];			
		
	if(timerStatus == 1)
	begin
		if(timer == 25'd25000000)
		begin
			LEDR[0] <= ~LEDR[0];
			timer <= 25'd0;
			if(sec == 6'd40) LEDR[0] <= 1'b0;
				else sec <= sec + 1'b1;
			end
				else timer <= timer + 1'b1;
		end
	else sec <= 5'd0;
end

	
always @ (negedge(evt))
begin
	if(KEY[2] == 0 && SW[9] == 0 && lockOut == 0)
	//RESET
	begin
		pin[0] <= 4'd0;
		pin[1] <= 4'd0;	
		pin[2] <= 4'd0;
		pin[3] <= 4'd0;

		puk[0] <= 4'd9;
		puk[1] <= 4'd9;
		puk[2] <= 4'd9;
		puk[3] <= 4'd9;
		puk[4] <= 4'd9;
		puk[5] <= 4'd9;
		puk[6] <= 4'd9;
		puk[7] <= 4'd9;
				
		timerStatus <= 1'b0;
				
		HEX3 <= ~7'b0000000; // Off
		HEX2 <= ~7'b0000000; // Off
		HEX1 <= ~7'b0000000; // Off
		HEX0 <= ~7'b0000000; // Off
		end
	else if(KEY[1] == 0)
	// CLEAR / INIT
	begin
		HEX3 <= ~7'b0000000; // Off
		HEX2 <= ~7'b0000000; // Off
		HEX1 <= ~7'b0000000; // Off
		HEX0 <= ~7'b0000000; // Off
				
		pinIndex <= 2'd0;
		pukIndex <= 3'd0;
								
		timerStatus <= (lockOut == 1 || SW[9] == 1) ? 0 : 1;		
	end
	else if(KEY[0] == 0 && (attemptsByTime >= 3 || attemptsByError >= 3))
	// LOCKOUT EVENT
	begin
		timerStatus <= 1'd0;
		lockOut <= 1'b1;
		attemptsByError <= 2'd0;
		attemptsByTime <= 2'd0;
				
		HEX3 <= ~7'b0111000; // Letter L
		HEX2 <= ~7'b1011100; // Letter o
		HEX1 <= ~7'b0011100; // Letter u
		HEX0 <= ~7'b1111000; // Letter t
	end
	else if(KEY[0] == 0 && sec >= 6'd40)
	// TIMEOUT EVENT
	begin	
		attemptsByTime <= attemptsByTime + 1'b1;		
		timerStatus <= 1'd0;
				
		HEX3 <= ~7'b1111000; // Letter t
		HEX2 <= ~7'b1011100; // Letter o
		HEX1 <= ~7'b0011100; // Letter u
		HEX0 <= ~7'b1111000; // Letter t
	end
	else if(KEY[0] == 0 && SW[9] == 1 && lockOut == 0)
	// ENTER KEY PRESSED IN PIN CHANGE MODE
	begin
		if(pinIndex < 4)
		begin
			case(pinIndex)
			0:begin
				HEX0 <= wrHEX (SW[3:0]);
				pinTemp[0] = SW[3:0];
			end
			1:begin
				HEX1 <= wrHEX (SW[3:0]);
				pinTemp[1] = SW[3:0];
			end
			2: begin
				HEX2 <= wrHEX (SW[3:0]);
				pinTemp[2] = SW[3:0];
			end
			3: begin
				HEX3 <= wrHEX (SW[3:0]);
				pinTemp[3] = SW[3:0];
			end
			endcase
			
			pinIndex <= pinIndex + 1'b1;
		end	
		else if(pinIndex == 4)
		begin
			pin[0] = pinTemp[0];
			pin[1] = pinTemp[1];
			pin[2] = pinTemp[2];
			pin[3] = pinTemp[3];
										
			HEX3 <= ~7'b0000000; // Off
			HEX2 <= ~7'b1101101; // Letter S
			HEX1 <= ~7'b1111000; // Letter t
			HEX0 <= ~7'b1010000; // Letter r
				
			pinIndex <= 2'd0;
			attemptsByError <= 2'd0;
			attemptsByTime <= 2'd0;
		end
	end
	else if(KEY[0] == 0 && SW[9] == 0 && lockOut == 0)
	// ENTER KEY PRESSED IN PIN INSERT MODE
	begin
		if(timerStatus == 1 && attemptsByError < 3 && attemptsByTime < 3)
		begin
			if(pinIndex < 4)
			begin
				case(pinIndex)
					0:begin
						HEX0 <= wrHEX (SW[3:0]);
						pinTemp[0] = SW[3:0];
					end
					1:begin
						HEX1 <= wrHEX (SW[3:0]);
						pinTemp[1] = SW[3:0];
					end
					2: begin
						HEX2 <= wrHEX (SW[3:0]);
						pinTemp[2] = SW[3:0];
					end
					3: begin
						HEX3 <= wrHEX (SW[3:0]);
						pinTemp[3] = SW[3:0];
					end
				endcase
				pinIndex <= pinIndex + 1'b1;
			end
			else if(pinIndex == 4)
			begin
				if(pin[0] == pinTemp[0] && pin[1] == pinTemp[1] && 
				   pin[2] == pinTemp[2] && pin[3] == pinTemp[3])
				begin
					HEX3 <= ~7'b0000000; // Off
					HEX2 <= ~7'b1011100; // Letter o
					HEX1 <= ~7'b1010100; // Letter n
					HEX0 <= ~7'b0000000; // Off
										
					attemptsByError <= 2'd0;
					attemptsByTime <= 2'd0;
					pinIndex <= 2'd0;
					timerStatus <= 1'd0;
				end
				else
				begin
					timerStatus <= 1'b0;
					attemptsByError <= attemptsByError + 1'b1;
										
					HEX3 <= ~7'b0000000; // Off
					HEX2 <= ~7'b1111001; // Letter E
					HEX1 <= ~7'b1010000; // Letter r
					HEX0 <= ~7'b1010000; // Letter r
				end
			end	
		end
	end
	else if(KEY[0] == 0 && SW[9] == 1 && lockOut == 1)
	// ENTER KEY PRESSED IN PUK INSERT MODE
	begin
		if(pukIndex < 8)
		begin
			case(pukIndex)
				0:begin
					HEX0 <= wrHEX (SW[3:0]);
					pukTemp[0] = SW[3:0];
				end
				1:begin
					HEX1 <= wrHEX (SW[3:0]);
					pukTemp[1] = SW[3:0];
				end
				2: begin
					HEX2 <= wrHEX (SW[3:0]);
					pukTemp[2] = SW[3:0];
				end
				3: begin
					HEX3 <= wrHEX (SW[3:0]);
					pukTemp[3] = SW[3:0];
				end
				4: begin
					HEX3 <= ~7'b0000000; // Off
					HEX2 <= ~7'b0000000; // Off
					HEX1 <= ~7'b0000000; // Off
					HEX0 <= wrHEX (SW[3:0]);
					pukTemp[4] = SW[3:0];
				end
				5: begin
					HEX1 <= wrHEX (SW[3:0]);
					pukTemp[5] = SW[3:0];
				end
				6: begin
					HEX2 <= wrHEX (SW[3:0]);
					pukTemp[6] = SW[3:0];
				end
				7: begin
					HEX3 <= wrHEX (SW[3:0]);
					pukTemp[7] = SW[3:0];
				end
			endcase
			pukIndex <= pukIndex + 1'b1;
		end
		else if(pukIndex == 8)
		begin
			if(puk[0] == pukTemp[0] && puk[1] == pukTemp[1] && 
			   puk[2] == pukTemp[2] && puk[3] == pukTemp[3] &&
			   puk[4] == pukTemp[4] && puk[5] == pukTemp[5] && 
			   puk[6] == pukTemp[6] && puk[7] == pukTemp[7])
			begin
				HEX3 <= ~7'b0000000; // Off
				HEX2 <= ~7'b1011100; // Letter o
				HEX1 <= ~7'b1010100; // Letter n
				HEX0 <= ~7'b0000000; // Off
										
				lockOut = 1'b0;
				attemptsByError <= 2'd0;
				attemptsByTime <= 2'd0;
				pukIndex <= 3'd0;
			end
			else
			begin
				HEX3 <= ~7'b0000000; // Off
				HEX2 <= ~7'b1111001; // Letter E
				HEX1 <= ~7'b1010000; // Letter r
				HEX0 <= ~7'b1010000; // Letter r
								
				pukIndex <= 3'd0;
			end
		end
	end
end


function [6:0] wrHEX (input [3:0] value);
	/*
	Encodes the input value into a suitable representation 
	for the 7 segment display.
	Returns the 7 segment display encoding.
	*/
	case (value)
		0: wrHEX= ~7'b0111111;
		1: wrHEX= ~7'b0000110;
		2: wrHEX= ~7'b1011011;
		3: wrHEX= ~7'b1001111;
		4: wrHEX= ~7'b1100110;
		5: wrHEX= ~7'b1101101;
		6: wrHEX= ~7'b1111101;
		7: wrHEX= ~7'b0000111;
		8: wrHEX= ~7'b1111111;
		9: wrHEX= ~7'b1101111;
		default : wrHEX = 7'b1111111; // OFF
	endcase
endfunction

endmodule
